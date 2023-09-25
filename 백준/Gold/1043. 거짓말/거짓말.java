import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		boolean[] knows;
		boolean[][] graph;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		knows = new boolean[N + 1];
		graph = new boolean[N + 1][N + 1];

		/////////////////////////////////////////////

		st = new StringTokenizer(br.readLine());
		int number = Integer.parseInt(st.nextToken());
		for (int i = 0; i < number; i++)
			knows[Integer.parseInt(st.nextToken())] = true;

		//////////////////////////////////////////////

		boolean[][] parties = new boolean[M][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			number = Integer.parseInt(st.nextToken());
			for (int j = 0; j < number; j++) {
				parties[i][Integer.parseInt(st.nextToken())] = true;
			}
		}

		//////////////////////////////////////////////

		for (int i = 0; i < M; i++) {
			for (int j = 1; j <= N; j++) {
				if (parties[i][j]) {
					for (int k = j + 1; k <= N; k++) {
						if (parties[i][k]) {
							graph[j][k] = true;
							graph[k][j] = true;
						}
					}
				}
			}
		}

//////////////////////////////////////////////
////////////////////////////////////////////
////////////////////////////////////////////

//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}

		boolean[] visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (knows[i] && !visited[i]) {
				dfs(graph, visited, i);
			}
		}

//		for (int i = 1; i <= N; i++) {
//			System.out.print(visited[i] + " ");
//		}

		int answer = 0;
		for (int i = 0; i < parties.length; i++) {
			boolean can = true;
			for (int j = 1; j < parties[i].length; j++) {
				if (parties[i][j]) {
					if (visited[j])
						can = false;
				}
			}
			if (can)
				answer++;
		}
		
		System.out.println(answer);
	}

	public static void dfs(boolean[][] graph, boolean[] visited, int cur) {
		visited[cur] = true;

		for (int i = 1; i < graph.length; i++) {
			if (graph[cur][i] && !visited[i]) {
				dfs(graph, visited, i);
			}
		}
	}
}