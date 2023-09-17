import java.io.*;
import java.util.*;

public class Main {

	public static boolean can;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = 0;
		while (true) {
			test_case++;
			int n, m;

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0)
				break;

			int[][] graph = new int[n + 1][n + 1];

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				graph[v1][v2] = 1;
				graph[v2][v1] = 1;
			}

			//////////////////////////////////////////////

			int tree_num = 0;

			boolean[] visited = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					can = true;
					dfs(graph, visited, i);
					if (can) {
						tree_num++;
					}
				}
			}

			if (tree_num == 0) {
				sb.append("Case ").append(test_case).append(": No trees.").append("\n");
			} else if (tree_num == 1) {
				sb.append("Case ").append(test_case).append(": There is one tree.").append("\n");
			} else {
				sb.append("Case ").append(test_case).append(": A forest of ").append(tree_num).append(" trees.")
						.append("\n");
			}
		}

		System.out.println(sb);
	}

	public static void dfs(int[][] graph, boolean[] visited, int index) {
		visited[index] = true;

		for (int i = 1; i < graph.length; i++) {
			if (graph[index][i] == 1) {
				if (visited[i]) {
					can = false;
				} else {
					graph[i][index] = 0;
					graph[index][i] = 0;
					dfs(graph, visited, i);
					graph[i][index] = 1;
					graph[index][i] = 1;
				}
			}
		}

	}
}
