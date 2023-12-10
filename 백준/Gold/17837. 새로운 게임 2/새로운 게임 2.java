import java.io.*;
import java.util.*;

class Horse {
    int number;
    int direction;

    Horse(int number, int direction) {
        this.number = number;
        this.direction = direction;
    }
}

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    public static int[] dy = new int[]{0, 0, -1, 1};
    public static int[] dx = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 2100 시작

        int N, K;
        int[][] board;
        Deque<Horse>[][] deque;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < board.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        deque = new ArrayDeque[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                deque[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken()) - 1;
            deque[y][x].addLast(new Horse(i, direction));
        }

//        for(int i =0 ; i < N ; i++){
//            for(int j =0 ; j < N ; j++){
//                for(Horse horse : deque[i][j]){
//                    System.out.println(i +  " " + j);
//                    System.out.println(horse.number + " " + horse.direction);
//                    System.out.println("---------------");
//                }
//            }
//        }

        ///////////////////////////////////////////////////////////////////

        int answer = 0;

        while (true) {
            answer += 1;

            if (answer > 1000) {
                sb.append("-1");
                break;
            }

            for (int k = 0; k < K; k++) {
                Point curPoint = null;
                Horse curHorse = null;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        for (Horse horse : deque[i][j]) {
                            if (horse.number == k) {
                                curPoint = new Point(i, j);
                                curHorse = horse;
                            }
                        }
                    }
                }

                int newY = curPoint.y + dy[curHorse.direction];
                int newX = curPoint.x + dx[curHorse.direction];

                //  0은 흰색, 1은 빨간색, 2는 파란색이다.
                int nextColor = -1;

                // 범위 안에 있다
                if (newY >= 0 && newY < board.length && newX >= 0 && newX < board[0].length) {
                    nextColor = board[newY][newX];
                } else {
                    nextColor = 2;
                }

                if (nextColor == 2) {
                    if (curHorse.direction == 0)
                        curHorse.direction = 1;
                    else if (curHorse.direction == 1)
                        curHorse.direction = 0;
                    else if (curHorse.direction == 2)
                        curHorse.direction = 3;
                    else if (curHorse.direction == 3)
                        curHorse.direction = 2;
                    else
                        System.out.println("curHorse.direction 오류");

                    newY = curPoint.y + dy[curHorse.direction];
                    newX = curPoint.x + dx[curHorse.direction];

                    // 범위 안에 있다
                    if (newY >= 0 && newY < board.length && newX >= 0 && newX < board[0].length) {
                        nextColor = board[newY][newX];
                    } else {
                        nextColor = 2;
                    }
                }

                if (nextColor == 0) {
                    Deque<Horse> tmp = new ArrayDeque<>();

                    while (deque[curPoint.y][curPoint.x].peekLast().number != k) {
                        tmp.addFirst(deque[curPoint.y][curPoint.x].removeLast());
                    }
                    tmp.addFirst(deque[curPoint.y][curPoint.x].removeLast());

                    while (!tmp.isEmpty()) {
                        deque[newY][newX].addLast(tmp.removeFirst());
                    }

                    if (deque[newY][newX].size() >= 4) {
                        System.out.println(answer);
                        System.exit(0);
                    }


                } else if (nextColor == 1) {
                    Deque<Horse> tmp = new ArrayDeque<>();

                    while (deque[curPoint.y][curPoint.x].peekLast().number != k) {
                        tmp.addLast(deque[curPoint.y][curPoint.x].removeLast());
                    }
                    tmp.addLast(deque[curPoint.y][curPoint.x].removeLast());

                    while (!tmp.isEmpty()) {
                        deque[newY][newX].addLast(tmp.removeFirst());
                    }

                    if (deque[newY][newX].size() >= 4) {
                        System.out.println(answer);
                        System.exit(0);
                    }

                }
                // 파란색인 경우
                else if (nextColor == 2) {

                } else {
                    System.out.println("nextColor 버그 찾지 못함");
                }

//                System.out.println("--------------------");
//                for (int i = 0; i < N; i++) {
//                    for (int j = 0; j < N; j++) {
//                        System.out.print(deque[i][j].size() + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("--------------------");

            }


        }

        if (sb.length() == 0)
            sb.append(answer);

        System.out.println(sb);


    }


}
