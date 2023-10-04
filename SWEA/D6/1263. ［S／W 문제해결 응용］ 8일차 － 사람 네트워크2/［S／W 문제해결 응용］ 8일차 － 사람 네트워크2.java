import java.io.*;
import java.util.*;

class Data {
	int vertex;
	int depth;

	Data(int vertex, int depth) {
		this.vertex = vertex;
		this.depth = depth;
	}
}

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			int N;
			boolean[][] graph;

			//////////////////////////////////////

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			graph = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (st.nextToken().equals("1"))
						graph[i][j] = true;
				}
			}

			//////////////////////////////////////

			int[][] dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				boolean[] visited = new boolean[N];
				Queue<Data> queue = new LinkedList<>();

				queue.add(new Data(i, 0));
				visited[i] = true;

				while (!queue.isEmpty()) {
					Data tmp = queue.remove();

					dist[i][tmp.vertex] = tmp.depth;

					for (int j = 0; j < N; j++) {
						if (graph[tmp.vertex][j] && !visited[j]) {
							visited[j] = true;
							queue.add(new Data(j, tmp.depth + 1));
						}
					}
				}
			}

			int[] sum = new int[N];

			for (int i = 0; i < N; i++) {

				for (int j = 0; j < N; j++) {
					sum[i] += dist[i][j];
				}
			}

			int answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				answer = Math.min(answer, sum[i]);
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");

		}
		System.out.println(sb);

	}

}