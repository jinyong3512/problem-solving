import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N;
			int M;
			int[][] graph;

			///////////////////////////////////////////////////

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			graph = new int[N][N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a - 1][b - 1] = 1;
			}

			///////////////////////////////////////////////////

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				boolean[] visited = new boolean[N];
				dfs(graph, arr, i, i, visited);
			}

//			for (int i = 0; i < arr.length; i++) {
//				for (int j = 0; j < arr[0].length; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}

			int answer = 0;
			for (int i = 0; i < arr.length; i++) {
				boolean can = true;
				for (int j = 0; j < arr[0].length; j++) {
					if (arr[i][j] == 0 && arr[j][i] == 0)
						can = false;
				}
				if (can)
					answer++;
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");

		}
		System.out.println(sb);

	}

	public static void dfs(int[][] graph, int[][] arr, int start, int cur, boolean[] visited) {
		visited[cur] = true;
		arr[start][cur] = 1;

		for (int i = 0; i < graph.length; i++) {
			if (graph[cur][i] == 1 && !visited[i]) {
				dfs(graph, arr, start, i, visited);
			}
		}
	}
}