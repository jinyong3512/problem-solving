import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            graph[start][end] = Math.min(graph[start][end], weight);
        }

        ////////////////////////////////////////////////////////////

        long[] distances = new long[N];
        for (int i = 0; i < N; i++) {
            distances[i] = Long.MAX_VALUE;
        }
        distances[0] = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (graph[j][k] == Integer.MAX_VALUE)
                        continue;
                    if (distances[j] == Long.MAX_VALUE)
                        continue;
                    if (distances[j] + graph[j][k] < distances[k])
                        distances[k] = distances[j] + graph[j][k];
                }
            }
        }

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (graph[j][k] == Integer.MAX_VALUE)
                    continue;
                if (distances[j] == Long.MAX_VALUE)
                    continue;
                if (distances[j] + graph[j][k] < distances[k]) {
                    System.out.println("-1");
                    System.exit(0);
                }
            }
        }

        for (int i = 1; i < N; i++) {
            if (distances[i] == Long.MAX_VALUE)
                sb.append("-1").append("\n");
            else
                sb.append(distances[i]).append("\n");
        }
        System.out.println(sb);

    }
}