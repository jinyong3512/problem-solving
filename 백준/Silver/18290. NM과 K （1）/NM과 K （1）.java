import java.io.*;
import java.util.*;

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        boolean[][] visited = new boolean[N][M];
        recursion(N, M, K, arr, visited, 0, 0, -1);
        System.out.println(answer);
    }

    public static void recursion(int N, int M, int K, int[][] arr, boolean[][] visited, int depth, int sum, int index) {

        if(depth == K){
            answer = Math.max(answer,sum);
            return;
        }

        for (int i = index + 1; i < N * M; i++) {

            int newY = i / M;
            int newX = i % M;

            if (newY - 1 >= 0 && visited[newY - 1][newX])
                continue;
            if (newX - 1 >= 0 && visited[newY][newX - 1])
                continue;

            visited[newY][newX] = true;
            recursion(N, M, K, arr, visited, depth + 1, sum + arr[newY][newX], i);
            visited[newY][newX] = false;

        }

    }
}
