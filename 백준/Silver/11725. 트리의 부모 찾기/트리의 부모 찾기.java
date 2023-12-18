import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N이 10만이다 10^5 N^2이면 터진다

        int N;
        N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[N + 1];
        int[] parents = new int[N + 1];
        dfs(graph, 1, visited, parents, 0);

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);

    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int cur, boolean[] visited, int[] parents, int prev) {
        visited[cur] = true;
        parents[cur] = prev;

        for (int i = 0; i < graph.get(cur).size(); i++) {
            if (!visited[graph.get(cur).get(i)])
                dfs(graph, graph.get(cur).get(i), visited, parents, cur);
        }
    }
}