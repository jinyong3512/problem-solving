import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N;
		int[][] arr;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		////////////////////////////////////////////////////

		// 가로 세로 대각선
		int[][][] dp = new int[N][N][3];
		dp[0][1][0] = 1;

		int answer = 0;
		answer += recursion(arr, dp, N - 1, N - 1, 0);
		answer += recursion(arr, dp, N - 1, N - 1, 1);
		answer += recursion(arr, dp, N - 1, N - 1, 2);

//		for (int k = 0; k < 3; k++) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(dp[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}

		System.out.println(answer);

	}

	// 0 가로 1 세로 2대각선
	public static int recursion(int[][] arr, int[][][] dp, int i, int j, int direction) {
		if (i < 0 || j < 0)
			return 0;

		if (arr[i][j] == 1)
			return 0;

		if (dp[i][j][direction] != 0)
			return dp[i][j][direction];

		// 내 머리가 해당 i,j이고 방향이 정해져 있을 때 구하고 싶어
		// 해당 지점 가로 갯수는?
		if (direction == 0) {
			// 가로로 온거
			dp[i][j][0] += recursion(arr, dp, i, j - 1, 0);

			// 대각선이 회전한거
			dp[i][j][0] += recursion(arr, dp, i, j - 1, 2);
		}
		// 세로
		else if (direction == 1) {
			// 세로로 온거
			dp[i][j][1] += recursion(arr, dp, i - 1, j, 1);

			// 대각선이 회전한거
			dp[i][j][1] += recursion(arr, dp, i - 1, j, 2);
		}
		// 대각선
		else {
			if (i - 1 >= 0 && j - 1 >= 0 && arr[i][j - 1] != 1 && arr[i - 1][j] != 1) {
				dp[i][j][2] += recursion(arr, dp, i - 1, j - 1, 0);
				dp[i][j][2] += recursion(arr, dp, i - 1, j - 1, 1);
				dp[i][j][2] += recursion(arr, dp, i - 1, j - 1, 2);
			}
		}

		return dp[i][j][direction];

	}
}