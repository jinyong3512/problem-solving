import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

class Edge {
	int start, end, weight;

	Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}

public class Main { // 메인 클래스

	public static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//////////////////////////////////

		for (int i = 0; i < arr.length; i++) {
			boolean[] visited = new boolean[arr.length];
			visited[i] = true;
			dfs(arr, visited, i, 1, 0, i);
		}

		System.out.println(answer);

	}

	public static void dfs(int[][] arr, boolean[] visited, int index, int depth, int sum, int start) {
		if (depth == arr.length) {

			if (arr[index][start] != 0)
				answer = Math.min(answer, sum + arr[index][start]);

			return;
		}

		for (int i = 0; i < arr[0].length; i++) {
			if (!visited[i] && arr[index][i] != 0) {
				visited[i] = true;
				dfs(arr, visited, i, depth + 1, sum + arr[index][i], start);
				visited[i] = false;
			}
		}
	}

}