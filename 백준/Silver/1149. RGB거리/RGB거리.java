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
		arr = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		//////////////////////////////////////////////////////////////////

		int[][] dp = new int[N][3];
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1]) + arr[i][2];
		}
		
		System.out.println(Math.min(Math.min(dp[N-1][0],dp[N-1][1]),dp[N-1][2]));

	}

}