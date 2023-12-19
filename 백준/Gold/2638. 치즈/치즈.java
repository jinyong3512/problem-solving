import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        int answer = 0;

        while (true) {

            boolean canStop = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1)
                        canStop = false;
                }
            }
            if (canStop)
                break;

            answer++;

            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                if (!visited[i][0] && arr[i][0] == 0)
                    dfs(arr, i, 0, visited);
                if (!visited[i][M - 1] && arr[i][M - 1] == 0)
                    dfs(arr, i, M - 1, visited);
            }
            for (int j = 0; j < M; j++) {
                if (!visited[0][j] && arr[0][j] == 0)
                    dfs(arr, 0, j, visited);
                if (!visited[N - 1][j] && arr[N - 1][j] == 0)
                    dfs(arr, N - 1, j, visited);
            }

            int[][] newArr = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
//                    if (!visited[i][j]) {
                    if (arr[i][j] == 1) {
                        int tmpCount = 0;
                        for (int direction = 0; direction < 4; direction++) {
                            int newY = i + dy[direction];
                            int newX = j + dx[direction];
                            if (visited[newY][newX])
                                tmpCount++;
                        }

                        if (tmpCount >= 2)
                            newArr[i][j] = 0;
                        else
                            newArr[i][j] = 1;
                    }
                }
            }

            arr = newArr;
        }
        System.out.println(answer);
    }

    public static void dfs(int[][] arr, int i, int j, boolean[][] visited) {
        visited[i][j] = true;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                continue;

            if (arr[newY][newX] == 0 && !visited[newY][newX])
                dfs(arr, newY, newX, visited);
        }
    }
}