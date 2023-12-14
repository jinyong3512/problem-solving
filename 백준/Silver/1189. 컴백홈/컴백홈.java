import java.io.*;
import java.util.*;

public class Main {

    public static int answer = 0;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int R, C, K;
        char[][] arr;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = inputLine.charAt(j);
            }
        }

        //////////////////////////////////////////

        boolean[][] visited = new boolean[R][C];
        dfs(arr, R - 1, 0, 1, visited, K);
        System.out.println(answer);

    }

    public static void dfs(char[][] arr, int i, int j, int depth, boolean[][] visited, int K) {

        visited[i][j] = true;

        if (i == 0 && j == arr[0].length - 1) {
            if (depth == K) {
                answer++;
            }
            visited[i][j] = false;
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                continue;

            if (!visited[newY][newX] && arr[newY][newX] == '.') {
                dfs(arr, newY, newX, depth + 1, visited, K);
                visited[newY][newX] = false;
            }
        }

    }
}