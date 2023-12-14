import java.io.*;
import java.util.*;

public class Main {

    // 상하좌우
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static int[] bombNumberBallCount = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 15:00 시작
        int N, M;
        int[][] arr;
        int[][] magics;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        magics = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            magics[i][0] = Integer.parseInt(st.nextToken());
            magics[i][1] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////////////////////////

        int centerY = N / 2;
        int centerX = N / 2;

        for (int m = 0; m < M; m++) {

            // 얼음 파편 작동
            int direction = magics[m][0] - 1;
            int distance = magics[m][1];

            for (int dis = 1; dis <= distance; dis++) {
                int newY = centerY + dy[direction] * dis;
                int newX = centerX + dx[direction] * dis;
//                if (arr[newY][newX] == 1 || arr[newY][newX] == 2 || arr[newY][newX] == 3)
//                    bombNumberBallCount[arr[newY][newX]]++;
                arr[newY][newX] = 0;
            }

//            printArr(arr);

            // 배열을 deque에 넣기
            // 좌하 우우상상 좌좌좌하하하 우우우우상상상상 좌좌좌좌좌하하하하하 우6상6 좌6
            Deque<Integer> deque = new ArrayDeque<>();
            int curY = centerY;
            int curX = centerX;
            for (int depth = 1; depth < N; depth++) {
                if (depth % 2 == 1) {
                    //좌 2
                    for (int i = 0; i < depth; i++) {
                        curY += dy[2];
                        curX += dx[2];
                        deque.addLast(arr[curY][curX]);
                    }
                    //하 1
                    for (int i = 0; i < depth; i++) {
                        curY += dy[1];
                        curX += dx[1];
                        deque.addLast(arr[curY][curX]);
                    }
                } else {
                    //우 3
                    for (int i = 0; i < depth; i++) {
                        curY += dy[3];
                        curX += dx[3];
                        deque.addLast(arr[curY][curX]);
                    }
                    //상 0
                    for (int i = 0; i < depth; i++) {
                        curY += dy[0];
                        curX += dx[0];
                        deque.addLast(arr[curY][curX]);
                    }
                }
            }
            for (int i = 0; i < N - 1; i++) {
                curY += dy[2];
                curX += dx[2];
                deque.addLast(arr[curY][curX]);
            }

            // 끝에 가짜 구슬 제거
            while (!deque.isEmpty() && deque.peekLast() == 0) {
                deque.removeLast();
            }

//            printDeque(deque);

            // 당기기
            Deque<Integer> newDeque = new ArrayDeque<>();
            while (!deque.isEmpty()) {
                if (deque.peekFirst() == 0)
                    deque.removeFirst();
                else {
                    newDeque.addLast(deque.removeFirst());
                }
            }
            deque = newDeque;

//            printDeque(deque);

            while (true) {
                // 폭팔가자
                boolean bomb = false;
                newDeque = new ArrayDeque<>();
                int ballNumber = 0;
                int ballCount = 0;
                while (!deque.isEmpty()) {
                    int curBallNumber = deque.removeFirst();

                    if (ballNumber == curBallNumber) {
                        ballCount++;
                        newDeque.addLast(ballNumber);
                    } else {
                        if (ballCount >= 4) {
                            for (int cnt = 0; cnt < ballCount; cnt++) {
                                newDeque.removeLast();
                            }
                            if (ballNumber == 1 || ballNumber == 2 || ballNumber == 3)
                                bombNumberBallCount[ballNumber] += ballCount;
                            bomb = true;
                        }
                        ballNumber = curBallNumber;
                        ballCount = 1;
                        newDeque.addLast(ballNumber);
                    }
                }
                if (ballCount >= 4) {
                    for (int cnt = 0; cnt < ballCount; cnt++) {
                        newDeque.removeLast();
                    }
                    if (ballNumber == 1 || ballNumber == 2 || ballNumber == 3)
                        bombNumberBallCount[ballNumber] += ballCount;
                    bomb = true;
                }
                deque = newDeque;

                if (!bomb)
                    break;

//                printDeque(deque);

            }
            // 그룹핑하자
            newDeque = new ArrayDeque<>();
            int ballNumber = 0;
            int ballCount = 0;
            while (!deque.isEmpty()) {
                int curBallNumber = deque.removeFirst();

                if (ballNumber == curBallNumber) {
                    ballCount++;
                } else {
                    if (ballNumber == 0) {
                        ballNumber = curBallNumber;
                        ballCount = 1;
                    } else {
                        newDeque.addLast(ballCount);
                        newDeque.addLast(ballNumber);
                        ballNumber = curBallNumber;
                        ballCount = 1;
                    }
                }
            }
            if (ballNumber != 0) {
                newDeque.addLast(ballCount);
                newDeque.addLast(ballNumber);
            }
            deque = newDeque;

//            printDeque(deque);


            // 배열에 넣기
            int[][] newArr = new int[N][N];
            curY = centerY;
            curX = centerX;

            for (int depth = 1; depth < N; depth++) {
                if (depth % 2 == 1) {
                    //좌 2
                    for (int i = 0; i < depth; i++) {
                        curY += dy[2];
                        curX += dx[2];
                        if (deque.isEmpty())
                            break;
                        newArr[curY][curX] = deque.removeFirst();
                        
                    }
                    if (deque.isEmpty())
                        break;
                    //하 1
                    for (int i = 0; i < depth; i++) {
                        curY += dy[1];
                        curX += dx[1];
                        if (deque.isEmpty())
                            break;
                        newArr[curY][curX] = deque.removeFirst();
                        
                    }
                    if (deque.isEmpty())
                        break;
                } else {
                    //우 3
                    for (int i = 0; i < depth; i++) {
                        curY += dy[3];
                        curX += dx[3];
                        if (deque.isEmpty())
                            break;
                        newArr[curY][curX] = deque.removeFirst();
                        
                    }
                    if (deque.isEmpty())
                        break;
                    //상 0
                    for (int i = 0; i < depth; i++) {
                        curY += dy[0];
                        curX += dx[0];
                        if (deque.isEmpty())
                            break;
                        newArr[curY][curX] = deque.removeFirst();
                        
                    }
                    if (deque.isEmpty())
                        break;
                }
            }
            for (int i = 0; i < N - 1; i++) {
                curY += dy[2];
                curX += dx[2];
                if (deque.isEmpty())
                    break;
                newArr[curY][curX] = deque.removeFirst();
                
            }

            arr = newArr;

//            printArr(arr);


        }

        System.out.println(bombNumberBallCount[1] + bombNumberBallCount[2] * 2 + bombNumberBallCount[3] * 3);


    }

    public static void printArr(int[][] arr) {
        System.out.println("-------------");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    public static void printDeque(Deque<Integer> deque) {
        System.out.println("-------------");
        for (Integer value : deque) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println("-------------");
    }
}