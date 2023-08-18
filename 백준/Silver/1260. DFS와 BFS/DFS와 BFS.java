import java.io.*;
import java.util.*;

//

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int N, M, V;
		ArrayList<ArrayList<Integer>> graph;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			graph.add(tmp);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}

		////////////////////////////////////////////////

		for (int i = 1; i < graph.size(); i++)
			Collections.sort(graph.get(i));

		boolean[] visited;

		visited = new boolean[N + 1];
		visited[V] = true;
		sb.append(V).append(" ");
		dfs(graph, V, visited, sb);
		sb.append("\n");

		visited = new boolean[N + 1];
		visited[V] = true;
		sb.append(V).append(" ");
		bfs(graph, V, visited, sb);
		sb.append("\n");

		System.out.println(sb);
	}

	public static void dfs(ArrayList<ArrayList<Integer>> graph, int V, boolean[] visited, StringBuilder sb) {

		for (int i = 0; i < graph.get(V).size(); i++) {
			if (!visited[graph.get(V).get(i)]) {
				sb.append(graph.get(V).get(i)).append(" ");
				visited[graph.get(V).get(i)] = true;
				dfs(graph, graph.get(V).get(i), visited, sb);
			}
		}
	}

	public static void bfs(ArrayList<ArrayList<Integer>> graph, int V, boolean[] visited, StringBuilder sb) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(V);

		while (!queue.isEmpty()) {
			int tmp = queue.remove();

			for (int i = 0; i < graph.get(tmp).size(); i++) {
				if (!visited[graph.get(tmp).get(i)]) {
					sb.append(graph.get(tmp).get(i)).append(" ");
					visited[graph.get(tmp).get(i)] = true;
					queue.add(graph.get(tmp).get(i));
				}
			}
		}
	}

}