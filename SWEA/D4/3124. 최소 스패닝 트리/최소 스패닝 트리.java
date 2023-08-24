import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

class Edge {
	int end;
	long weight;

	Edge(int end, long weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Solution { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int V, E;
			int A, B, C;
			ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			for (int i = 0; i <= V; i++)
				graph.add(new ArrayList<Edge>());

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				graph.get(A).add(new Edge(B, C));
				graph.get(B).add(new Edge(A, C));
			}
			///////////////////////////////////////////////////////////

			long answer = 0;
			boolean[] visited = new boolean[V + 1];
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

			visited[1] = true;
			for (int i = 0; i < graph.get(1).size(); i++)
				pQ.add(graph.get(1).get(i));

			while (!pQ.isEmpty()) {
				Edge tmp = pQ.remove();

				if (!visited[tmp.end]) {
					visited[tmp.end] = true;
					answer += tmp.weight;
					for (int i = 0; i < graph.get(tmp.end).size(); i++)
						pQ.add(graph.get(tmp.end).get(i));
				}
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");

		}

		System.out.println(sb);
	}
}