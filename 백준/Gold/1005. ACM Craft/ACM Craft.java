import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] Ds = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Ds[i] = Integer.parseInt(st.nextToken());
            }

            boolean[][] graph = new boolean[N][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken())-1;
                int Y = Integer.parseInt(st.nextToken())-1;
                graph[X][Y] = true;
            }

            int W = Integer.parseInt(br.readLine())-1;

            sb.append(solve(N, K, Ds, graph, W)).append("\n");
        }
        System.out.println(sb);
    }

    public static int solve(int N, int K, int[] Ds, boolean[][] graph, int W) {


        int[] takeTimes = new int[N];
        int[] degrees = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j])
                    degrees[j]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int start = 0; start < N; start++) {
            if (degrees[start] == 0) {
                queue.add(start);
                takeTimes[start] = Ds[start];
            }
        }

        while (!queue.isEmpty()) {
            int curVertex = queue.remove();

            for (int i = 0; i < N; i++) {
                if (graph[curVertex][i]) {
                    degrees[i]--;

                    if (degrees[i] == 0) {

                        int takeTime = 0;
                        for(int j = 0 ; j < N ; j++){
                            if(graph[j][i])
                                takeTime = Math.max(takeTime, takeTimes[j]);
                        }
                        queue.add(i);
                        takeTimes[i] = takeTime + Ds[i];
                    }
                }
            }
        }

//        for(int value: takeTimes)
//            System.out.print(value+" ");
//        System.out.println();

        return takeTimes[W];

    }
}
