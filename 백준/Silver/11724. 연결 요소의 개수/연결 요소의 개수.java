import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

		//////////////////////////////////////////////////

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}

		/////////////////////////////////////////////////

		int answer = 0;
		boolean[] visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				answer++;
				dfs(graph, visited, i);
			}
		}
		
		System.out.println(answer);

	}

	public static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int cur) {
		visited[cur] = true;

		for (int i = 0; i < graph.get(cur).size(); i++) {
			if (!visited[graph.get(cur).get(i)]) {
				dfs(graph, visited, graph.get(cur).get(i));
			}
		}
	}
}