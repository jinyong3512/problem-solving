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

        boolean[][] graph = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tmp - 1; j++) {
                int cur = Integer.parseInt(st.nextToken());
                graph[prev][cur] = true;

                prev = cur;
            }
        }

        //////////////////////////////////////////////////

        int[] degrees = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j])
                    degrees[j]++;
            }
        }

        Queue<Integer> answer = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (degrees[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            answer.add(cur);

            for (int j = 1; j <= N; j++) {
                if (graph[cur][j]) {
                    degrees[j]--;
                    if (degrees[j] == 0) {
                        queue.add(j);
                    }
                }
            }
        }

        if (answer.size() != N) {
            sb.append("0").append("\n");
        } else {
            while (!answer.isEmpty()) {
                sb.append(answer.remove()).append("\n");
            }
        }

        System.out.println(sb);

    }
}