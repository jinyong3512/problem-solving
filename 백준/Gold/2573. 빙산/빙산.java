import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////////////////

        int takeTime = 0;
        while (true) {

            if (allOfZero(arr)) {
                sb.append("0");
                break;
            }

            if (countDunguri(arr) >= 2) {
                sb.append(takeTime);
                break;
            } else {
                arr = spreadArr(arr);
            }

            takeTime++;

        }

        sb.append("\n");
        System.out.println(sb);
    }

    public static boolean allOfZero(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0)
                    return false;
            }
        }

        return true;
    }

    public static int countDunguri(int[][] arr) {

        int count = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (!visited[i][j] && arr[i][j]!=0) {
                    count++;
                    dfs(arr, i, j, visited);
                }
            }
        }

//        System.out.println(count);

        return count;
    }

    public static void dfs(int[][] arr, int i, int j, boolean[][] visited) {
        visited[i][j] = true;

        // 위로
        if (i - 1 >= 0 && !visited[i - 1][j] && arr[i - 1][j] != 0)
            dfs(arr, i - 1, j, visited);

        // 아래로
        if (i + 1 < arr.length && !visited[i + 1][j] && arr[i + 1][j] != 0)
            dfs(arr, i + 1, j, visited);

        // 왼쪽
        if (j - 1 >= 0 && !visited[i][j - 1] && arr[i][j - 1] != 0)
            dfs(arr, i, j - 1, visited);

        // 오른쪽
        if (j + 1 < arr[0].length && !visited[i][j + 1] && arr[i][j + 1] != 0)
            dfs(arr, i, j + 1, visited);


    }


    private static int[][] spreadArr(int[][] arr) {

        int[][] new_arr = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    int countZero = 0;

                    if (i - 1 >= 0 && arr[i - 1][j] == 0)
                        countZero++;
                    if (i + 1 < arr.length && arr[i + 1][j] == 0)
                        countZero++;
                    if (j - 1 >= 0 && arr[i][j - 1] == 0)
                        countZero++;
                    if (j + 1 < arr[0].length && arr[i][j + 1] == 0)
                        countZero++;

                    if (arr[i][j] - countZero < 0)
                        new_arr[i][j] = 0;
                    else
                        new_arr[i][j] = arr[i][j] - countZero;
                }
            }
        }

        return new_arr;
    }
}
