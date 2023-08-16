import java.io.*;
import java.util.*;

public class Solution {

	public static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N, M;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[][] graph = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				graph[v1][v2] = 1;
				graph[v2][v1] = 1;
			}

			//////////////////////////////////////////////////

			answer = 0;
			for (int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N + 1];
				visited[i] = true;
				dfs(graph, visited, 1, i);
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);

	}

	public static void dfs(int[][] graph, boolean[] visited, int depth, int index) {
		answer = Math.max(answer, depth);

		for (int i = 1; i < graph.length; i++) {
			if (graph[index][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(graph, visited, depth + 1, i);
				visited[i] = false;
			}
		}

	}
}