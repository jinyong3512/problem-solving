import java.io.*;
import java.util.*;

public class Main {

    public static int tmpAnswer2;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 23:06 시작

        int N, Q;
        int[][] arr;
        int[] Ls;

        ///////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Ls = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            Ls[i] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////

        // Ls[i]가 1일 경우 2x2로 격자를 놔누고 회전 시키는 거임

        for (int LsIndex = 0; LsIndex < Ls.length; LsIndex++) {

            int length = (int) Math.pow(2, Ls[LsIndex]);

            int[][] newArr = new int[arr.length][arr[0].length];
            divideAndConquer(arr, 0, 0, arr.length, length, newArr);

//            for (int i = 0; i < newArr.length; i++) {
//                for (int j = 0; j < newArr[0].length; j++) {
//                    System.out.print(newArr[i][j] + " ");
//                }
//                System.out.println();
//            }

            arr = newArr;

            // 인접 칸이 얼음이 0개 1개 2개 면 -1

            newArr = new int[arr.length][arr[0].length];

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    int countNotZero = 0;

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = i + dy[direction];
                        int newX = j + dx[direction];

                        if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                            continue;

                        if (arr[newY][newX] != 0)
                            countNotZero++;
                    }

                    if (countNotZero >= 3) {
                        newArr[i][j] = arr[i][j];
                    } else {
                        if (arr[i][j] != 0)
                            newArr[i][j] = arr[i][j] - 1;

                    }

                }
            }

            arr = newArr;
        }

        int answer1 = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                answer1 += arr[i][j];
            }
        }

        int answer2 = 0;

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (!visited[i][j] && arr[i][j] != 0) {
                    tmpAnswer2 = 0;
                    dfs(arr, i, j, visited);
                    answer2 = Math.max(answer2, tmpAnswer2);
                }
            }
        }

        System.out.println(answer1);
        System.out.println(answer2);


    }

    public static void divideAndConquer(int[][] arr, int y, int x, int bigSize, int smallSize, int[][] newArr) {
//        System.out.println(" y = " + y + " x = " + x + "  bigSize = " + bigSize);

        if (bigSize == smallSize) {


            for (int i = 0; i < smallSize; i++) {
                for (int j = 0; j < smallSize; j++) {

                    // y,x 에서

                    newArr[y + i][x + j] = arr[y + smallSize - j - 1][x + i];
                }
            }

            return;
        }

        divideAndConquer(arr, y, x, bigSize / 2, smallSize, newArr);
        divideAndConquer(arr, y + bigSize / 2, x, bigSize / 2, smallSize, newArr);
        divideAndConquer(arr, y, x + bigSize / 2, bigSize / 2, smallSize, newArr);
        divideAndConquer(arr, y + bigSize / 2, x + bigSize / 2, bigSize / 2, smallSize, newArr);
    }

    public static void dfs(int[][] arr, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        tmpAnswer2++;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                continue;

            if (!visited[newY][newX] && arr[newY][newX] != 0)
                dfs(arr, newY, newX, visited);
        }

    }
}