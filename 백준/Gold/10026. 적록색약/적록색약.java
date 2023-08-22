import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N;
		char[][] arr;

		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
			}
		}

		////////////////////////////////////////////

		int answer1 = 0;
		int answer2 = 0;

		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(arr, i, j, arr[i][j], visited);
					answer1++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 'R') {
					arr[i][j] = 'G';
				}
			}
		}

		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(arr, i, j, arr[i][j], visited);
					answer2++;
				}
			}
		}

		System.out.println(answer1 + " " + answer2);

	}

	public static void dfs(char[][] arr, int i, int j, char color, boolean[][] visited) {
		visited[i][j] = true;

		// 위로
		if (i - 1 >= 0 && arr[i - 1][j] == color && !visited[i - 1][j])
			dfs(arr, i - 1, j, color, visited);

		// 아래로
		if (i + 1 < arr.length && arr[i + 1][j] == color && !visited[i + 1][j])
			dfs(arr, i + 1, j, color, visited);

		// 왼쪽
		if (j - 1 >= 0 && arr[i][j - 1] == color && !visited[i][j - 1])
			dfs(arr, i, j - 1, color, visited);

		// 오른쪽
		if (j + 1 < arr[0].length && arr[i][j + 1] == color && !visited[i][j + 1])
			dfs(arr, i, j + 1, color, visited);
	}

}
