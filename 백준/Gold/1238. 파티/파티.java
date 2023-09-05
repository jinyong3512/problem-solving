import java.io.*;
import java.util.*;

class Edge {
	int end;
	int weight;

	Edge(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M, X;
		ArrayList<ArrayList<Edge>> graph;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph.get(Integer.parseInt(st.nextToken()))
					.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		/////////////////////////////////////////////////////////////////////////

		long[] answers = new long[N + 1];

		// 모든 정점이 X로 가야한다 다른 정점 신경 안쓰고 자기 자신만 이득 챙겨!
		// 한정점에서 특정 정점으로 제일 빨리 가는 방법! i-> X
		
		for (int k = 1; k <= N; k++) {
			long[] dist = new long[N + 1];
			for (int i = 0; i <= N; i++)
				dist[i] = Long.MAX_VALUE;
			boolean[] visited = new boolean[N + 1];
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

			dist[k] = 0;
			pQ.add(new Edge(k, 0));

			while (!pQ.isEmpty()) {
				Edge tmp = pQ.remove();

				visited[tmp.end] = true;

				for (int i = 0; i < graph.get(tmp.end).size(); i++) {
					int new_end = graph.get(tmp.end).get(i).end;
					int new_weight = graph.get(tmp.end).get(i).weight;
					if (!visited[new_end] && dist[new_end] > tmp.weight + new_weight) {
						dist[new_end] = tmp.weight + new_weight;
						pQ.add(new Edge(new_end, tmp.weight + new_weight));
					}
				}
			}

			answers[k] += dist[X];
		}
		// X에서 다시 자기자신으로 되돌아 가야 한다 최단거리로!
		// 한정점에서 모든 정점으로 가는 최단 거리 X - > all
		// 다익스트라

		long[] dist2 = new long[N + 1];
		for (int i = 0; i <= N; i++)
			dist2[i] = Long.MAX_VALUE;
		boolean[] visited2 = new boolean[N + 1];
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

		dist2[X] = 0;
		pQ.add(new Edge(X, 0));

		while (!pQ.isEmpty()) {
			Edge tmp = pQ.remove();

			visited2[tmp.end] = true;

			for (int i = 0; i < graph.get(tmp.end).size(); i++) {
				int new_end = graph.get(tmp.end).get(i).end;
				int new_weight = graph.get(tmp.end).get(i).weight;
				if (!visited2[new_end] && dist2[new_end] > tmp.weight + new_weight) {
					dist2[new_end] = tmp.weight + new_weight;
					pQ.add(new Edge(new_end, tmp.weight + new_weight));
				}

//				if (!visited2[new_end] && dist2[new_end] > dist2[tmp.end]+ new_weight) {
//					dist2[new_end] = dist2[tmp.end] + new_weight;
//					pQ.add(new Edge(new_end, (int) dist2[new_end]));
//				}
			}
		}

		for (int i = 0; i <= N; i++) {
			answers[i] += dist2[i];
		}
		
		int answer = Integer.MIN_VALUE;
		for(int i =0 ; i <= N ; i++)
			answer = (int) Math.max(answer,answers[i]);

		
		System.out.println(answer);
	}
}