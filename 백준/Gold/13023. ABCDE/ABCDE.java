import java.io.*;
import java.util.*;

public class Main {

	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		ArrayList<ArrayList<Integer>> graph;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i < N; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
		}

		//////////////////////////////////////////////////

		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			recursion(graph, visited, 1, i);
		}

		System.out.println(answer);
	}

	public static void recursion(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int depth, int index) {

		if (answer == 1)
			return;

		if (depth == 5) {
			answer = 1;
			return;
		}

		for (int i = 0; i < graph.get(index).size(); i++) {
			if (!visited[graph.get(index).get(i)]) {
				visited[graph.get(index).get(i)] = true;
				recursion(graph, visited, depth + 1, graph.get(index).get(i));
				visited[graph.get(index).get(i)] = false;
			}
		}
	}

}