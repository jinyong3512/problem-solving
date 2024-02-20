import java.io.*;
import java.util.*;

class Main {

    public static int[] dy = new int[]{-1, 0, 1, 0};
    public static int[] dx = new int[]{0, 1, 0, -1};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        //  0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.

        // 13:10

        int N, M;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //////////////////////////////////////////////////////////////////

        recursion(arr, 0);

        System.out.println(answer);

    }

    public static void recursion(int[][] arr, int index) {
        if (index == arr.length * arr[0].length) {

            int countZero = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] == 0)
                        countZero++;
                }
            }

            answer = Math.min(answer, countZero);


            return;
        }

        int y = index / arr[0].length;
        int x = index % arr[0].length;

        int[][] newArr;

        if (arr[y][x] == -1 || arr[y][x] == 0 || arr[y][x] == 6) {
            recursion(arr, index + 1);
        } else if (arr[y][x] == 1) {

            for (int direction = 0; direction < 4; direction++) {
                newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        newArr[i][j] = arr[i][j];
                    }
                }

                int newY = y;
                int newX = x;
                while (true) {
                    newY += dy[direction];
                    newX += dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }
                recursion(newArr, index + 1);

            }
        } else if (arr[y][x] == 2) {

            for (int direction = 0; direction < 2; direction++) {
                newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        newArr[i][j] = arr[i][j];
                    }
                }

                int newY = y;
                int newX = x;
                while (true) {
                    newY += dy[direction];
                    newX += dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }

                newY = y;
                newX = x;
                while (true) {
                    newY += dy[direction + 2];
                    newX += dx[direction + 2];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }


                recursion(newArr, index + 1);
            }

        } else if (arr[y][x] == 3) {

            for (int direction = 0; direction < 4; direction++) {
                newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        newArr[i][j] = arr[i][j];
                    }
                }

                int newY = y;
                int newX = x;
                while (true) {
                    newY += dy[direction];
                    newX += dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }

                newY = y;
                newX = x;
                while (true) {
                    newY += dy[(direction + 1) % 4];
                    newX += dx[(direction + 1) % 4];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }


                recursion(newArr, index + 1);
            }

        } else if (arr[y][x] == 4) {
            for (int direction = 0; direction < 4; direction++) {
                newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        newArr[i][j] = arr[i][j];
                    }
                }

                int newY = y;
                int newX = x;
                while (true) {
                    newY += dy[direction];
                    newX += dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }

                newY = y;
                newX = x;
                while (true) {
                    newY += dy[(direction + 1) % 4];
                    newX += dx[(direction + 1) % 4];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }

                newY = y;
                newX = x;
                while (true) {
                    newY += dy[(direction + 2) % 4];
                    newX += dx[(direction + 2) % 4];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }


                recursion(newArr, index + 1);
            }
        } else if (arr[y][x] == 5) {

            newArr = new int[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    newArr[i][j] = arr[i][j];
                }
            }


            for (int direction = 0; direction < 4; direction++) {

                int newY = y;
                int newX = x;
                while (true) {
                    newY += dy[direction];
                    newX += dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        break;

                    if (newArr[newY][newX] == 6)
                        break;
                    else if (newArr[newY][newX] == 0)
                        newArr[newY][newX] = -1;

                }
            }


            recursion(newArr, index + 1);

        }


    }
}

