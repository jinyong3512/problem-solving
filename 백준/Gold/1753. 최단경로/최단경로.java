import java.io.*;
import java.util.*;

class Edge {
	int end;
	long weight;

	Edge(int end, long weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++)
			graph.add(new ArrayList<Edge>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Edge(end, weight));
		}

		///////////////////////////////////////////////////////////////////

		PriorityQueue<Edge> pQ = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.weight > o2.weight)
					return 1;
				else if (o1.weight == o2.weight)
					return 0;
				else
					return -1;
			}
		});
		boolean[] visited = new boolean[V + 1];
		long[] dist = new long[V + 1];

		for (int i = 0; i < graph.get(K).size(); i++)
			pQ.add(graph.get(K).get(i));

		visited[K] = true;

		for (int i = 1; i <= V; i++)
			dist[i] = Long.MAX_VALUE;
		dist[K] = 0;

		while (!pQ.isEmpty()) {
			Edge edge = pQ.remove();

			if (!visited[edge.end]) {
				visited[edge.end] = true;
				dist[edge.end] = edge.weight;

				for (int i = 0; i < graph.get(edge.end).size(); i++) {
					pQ.add(new Edge(graph.get(edge.end).get(i).end,
							dist[edge.end] + graph.get(edge.end).get(i).weight));

				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Long.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}

	}
}