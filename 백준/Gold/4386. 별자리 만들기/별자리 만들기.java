import java.io.*;
import java.util.*;

public class Main {
	static double total;
	static List<Node>[] list;
	static boolean[] visited;
	static double[] starX;
	static double[] starY;

	static class Node implements Comparable<Node> {
		int to;
		double value;

		public Node(int to, double value) {
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine().trim());
		list = new ArrayList[N];
		visited = new boolean[N];
		starX = new double[N];
		starY = new double[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			starX[i] = Double.parseDouble(st.nextToken());
			starY[i] = Double.parseDouble(st.nextToken());
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double dist = Math.sqrt(Math.pow(starX[i] - starX[j], 2) + Math.pow(starY[i] - starY[j], 2));
				list[i].add(new Node(j, dist));
				list[j].add(new Node(i, dist));

			}
		}
		prim(0);
		System.out.println(String.format("%.2f", total));

	}

	static void prim(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node p = pq.poll();
			int node = p.to;
			double weight = p.value;
			if (visited[node])
				continue;
			visited[node] = true;
			total += weight;
			for (Node next : list[node]) {
				if (!visited[next.to]) {
					pq.add(next);
				}
			}

		}
	}

}