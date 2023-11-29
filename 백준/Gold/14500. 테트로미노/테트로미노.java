import java.io.*;
import java.util.*;

class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////////////////////

        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(arr, N, M, 1, arr[i][j], i, j, visited);
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int[][] arr, int N, int M, int depth, int sum, int y, int x, boolean[][] visited) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        if (depth == 2) {

            // 상 우
            if (y > 0 && x < M - 1 && !visited[y - 1][x] && !visited[y][x + 1])
                answer = Math.max(answer, sum + arr[y - 1][x] + arr[y][x + 1]);
            // 상 하
            if (y > 0 && y < N - 1 && !visited[y - 1][x] && !visited[y + 1][x])
                answer = Math.max(answer, sum + arr[y - 1][x] + arr[y + 1][x]);
            // 상 좌
            if (y > 0 && x > 0 && !visited[y - 1][x] && !visited[y][x - 1])
                answer = Math.max(answer, sum + arr[y - 1][x] + arr[y][x - 1]);

            // 우 하
            if (y < N - 1 && x < M - 1 && !visited[y][x + 1] && !visited[y + 1][x])
                answer = Math.max(answer, sum + arr[y][x + 1] + arr[y + 1][x]);
            // 우 좌
            if (x < M - 1 && x > 0 && !visited[y][x + 1] && !visited[y][x - 1])
                answer = Math.max(answer, sum + arr[y][x + 1] + arr[y][x - 1]);

            // 하 좌
            if (y < N - 1 && x > 0 && !visited[y + 1][x] && !visited[y][x - 1])
                answer = Math.max(answer, sum + arr[y + 1][x] + arr[y][x - 1]);

        }

        visited[y][x] = true;

        if (y > 0 && !visited[y - 1][x])
            dfs(arr, N, M, depth + 1, sum + arr[y - 1][x], y - 1, x, visited);
        if (y < N - 1 && !visited[y + 1][x])
            dfs(arr, N, M, depth + 1, sum + arr[y + 1][x], y + 1, x, visited);
        if (x > 0 && !visited[y][x - 1])
            dfs(arr, N, M, depth + 1, sum + arr[y][x - 1], y, x - 1, visited);
        if (x < M - 1 && !visited[y][x + 1])
            dfs(arr, N, M, depth + 1, sum + arr[y][x + 1], y, x + 1, visited);

        visited[y][x] = false;
    }
}