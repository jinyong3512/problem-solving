import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

//

public class Main { // 메인 클래스

	public static int answer = 0;

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N;
		int[][] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		////////////////////////////////////////////////////

		// 8P8 해서 simulation 돌려 그냥
		boolean[] visited = new boolean[9];
		ArrayList<Integer> arrayList = new ArrayList<>();
		permutation(arr, 8, 8, visited, arrayList, 0);
		System.out.println(answer);
	}

	public static void permutation(int[][] arr, int n, int r, boolean[] visited, ArrayList<Integer> arrayList,
			int depth) {

		if (depth == r) {
			arrayList.add(3, 0);
			simulation(arr, arrayList);
			arrayList.remove(3);
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arrayList.add(i);
				permutation(arr, n, r, visited, arrayList, depth + 1);
				visited[i] = false;
				arrayList.remove(arrayList.size() - 1);
			}
		}

	}

	public static void simulation(int[][] arr, ArrayList<Integer> arrayList) {
		int i = 0;
		int j = 0;

		int out_count = 0;
		int[] runner = new int[4];

		int arrayList_index = 0;
		int score = 0;

		// while문이 도는 기준은 한 사람마다
		while (i < arr.length) {
			// arrayList_index는 타순을 얻을 수 있는 0~8 숫자
			j = arrayList.get(arrayList_index);
			arrayList_index++;
			arrayList_index %= 9;

			// 아웃
			if (arr[i][j] == 0) {
				out_count++;
				// 이닝 종료
				if (out_count == 3) {
					i++; // 이닝 넘겨
					out_count = 0;
					runner = new int[4];
				}
			}
			// 안타
			else if (arr[i][j] == 1) {
				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 1;

			}
			// 2루타
			else if (arr[i][j] == 2) {
				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 1;

				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 0;
			}

			// 3루타
			else if (arr[i][j] == 3) {
				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 1;

				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 0;

				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
			}

			// 홈런
			else {
				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 1;

				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
				runner[0] = 0;

				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}

				for (int x = 3; x >= 1; x--) {
					runner[x] = runner[x - 1];
				}
				if (runner[3] == 1) {
					runner[3] = 0;
					score++;
				}
			}
		}

		answer = Math.max(answer, score);
	}

}