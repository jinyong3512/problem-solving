import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 	

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[1] = 0;

		for (int i = 2; i <= N; i++) {
			if (dp[i] == 0) {
				dp[i] = Integer.MAX_VALUE;

				if (i % 3 == 0) {
					dp[i] = Math.min(dp[i], dp[i / 3] + 1);
				}
				if (i % 2 == 0) {
					dp[i] = Math.min(dp[i], dp[i / 2] + 1);
				}
				dp[i] = Math.min(dp[i], dp[i - 1] + 1);
			}
		}
		
		System.out.println(dp[N]);

	}

}