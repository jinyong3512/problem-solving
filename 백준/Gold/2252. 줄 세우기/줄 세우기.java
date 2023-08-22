import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N, M;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
		}

		////////////////////////////////////////////////////////////////////////////////////////

		/*
		 * 큐를 이용하는 위상 정렬 알고리즘의 동작 과정은 다음과 같다 진입차수가 0인 모든 노드를 큐에 넣는다 큐가 빌 때까지 다음의 과정을
		 * 반복한다 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거한다 새롭게 진입차수가 0이 된 노드를 큐에 넣는다
		 */

		int[] in_degree = new int[N + 1];
		for (ArrayList<Integer> tmp : graph) {
			for (int num : tmp) {
				in_degree[num]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (in_degree[i] == 0)
				queue.add(i);
		}

		while (!queue.isEmpty()) {
			int tmp = queue.remove();

			sb.append(tmp).append(" ");

			for (int i = 0; i < graph.get(tmp).size(); i++) {
				in_degree[graph.get(tmp).get(i)]--;

				if (in_degree[graph.get(tmp).get(i)] == 0) {
					queue.add(graph.get(tmp).get(i));
				}
			}
		}

		System.out.println(sb);

	}
}