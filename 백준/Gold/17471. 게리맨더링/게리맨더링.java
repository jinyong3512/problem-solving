import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

public class Main { // 메인 클래스

	public static int answer = Integer.MAX_VALUE; // 최솟값 구해야 하니 맥스로 설정

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N;
		int[] populations;
		ArrayList<ArrayList<Integer>> graph;

		N = Integer.parseInt(br.readLine());
		populations = new int[N + 1];

		graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++)
			graph.add(new ArrayList<Integer>());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++)
			populations[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());

			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++)
				graph.get(i).add(Integer.parseInt(st.nextToken()));
		}

		//////////////////////////////////////////////////////////

		int all_sum = 0;

		for (int i = 1; i < N + 1; i++)
			all_sum += populations[i];

		boolean[] visited = new boolean[N + 1];
		combination(graph, populations, 0, 0, visited, all_sum, 0);

		if (answer == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(answer);

	}

	public static void combination(ArrayList<ArrayList<Integer>> graph, int[] populations, int last_pick, int depth,
			boolean[] visited, int all_sum, int sum) {
		if (depth == graph.size() - 1)
			return;

		int blue_dfs_count = 0;
		boolean[] visited1 = new boolean[visited.length];
		for (int i = 1; i < visited.length; i++)
			visited1[i] = !visited[i];

		for (int i = 1; i < visited1.length; i++) {
			if (!visited1[i]) {
				blue_dfs_count++;
				visited1[i] = true;
				dfs2(graph, visited1, i);
			}
		}

		// 판단하기
		int red_dfs_count = 0;
		boolean[] visited2 = new boolean[visited.length];
		for (int i = 1; i < visited.length; i++)
			visited2[i] = visited[i];

		for (int i = 1; i < visited2.length; i++) {
			if (!visited2[i]) {
				red_dfs_count++;
				visited2[i] = true;
				dfs2(graph, visited2, i);
			}
		}

		if (blue_dfs_count == 1 && red_dfs_count == 1)
			answer = Math.min(answer, Math.abs(Math.abs(all_sum - sum) - Math.abs(sum)));

		for (int i = last_pick + 1; i < graph.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(graph, populations, i, depth + 1, visited, all_sum, sum + populations[i]);
				visited[i] = false;
			}
		}
	}

	public static void dfs2(ArrayList<ArrayList<Integer>> graph, boolean[] visited2, int index) {
		for (int i = 0; i < graph.get(index).size(); i++) {
			if (!visited2[graph.get(index).get(i)]) {
				visited2[graph.get(index).get(i)] = true;
				dfs2(graph, visited2, graph.get(index).get(i));
			}
		}
	}

}