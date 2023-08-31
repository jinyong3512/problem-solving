import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			// dp i는 비밀번호 자리수 j는 해당 i 자릿수일때 끝자리 갯수
			long[][] dp = new long[N + 1][10];

			for (int i = 0; i <= 9; i++)
				dp[1][i] = 1;

			for (int i = 2; i <= N; i++) {
				dp[i][0] = dp[i - 1][7] % 1234567;
				dp[i][1] = dp[i - 1][2] % 1234567 + dp[i - 1][4] % 1234567;
				dp[i][2] = dp[i - 1][1] % 1234567 + dp[i - 1][3] % 1234567 + dp[i - 1][5] % 1234567;
				dp[i][3] = dp[i - 1][2] % 1234567 + dp[i - 1][6] % 1234567;
				dp[i][4] = dp[i - 1][1] % 1234567 + dp[i - 1][5] % 1234567 + dp[i - 1][7] % 1234567;
				dp[i][5] = dp[i - 1][2] % 1234567 + dp[i - 1][4] % 1234567 + dp[i - 1][6] % 1234567
						+ dp[i - 1][8] % 1234567;
				dp[i][6] = dp[i - 1][3] % 1234567 + dp[i - 1][5] % 1234567 + dp[i - 1][9] % 1234567;
				dp[i][7] = dp[i - 1][4] % 1234567 + dp[i - 1][8] % 1234567 + dp[i - 1][0] % 1234567;
				dp[i][8] = dp[i - 1][5] % 1234567 + dp[i - 1][7] % 1234567 + dp[i - 1][9] % 1234567;
				dp[i][9] = dp[i - 1][6] % 1234567 + dp[i - 1][8] % 1234567;
			}

			long answer = 0;

			for (int i = 0; i <= 9; i++) {
				answer = answer + dp[N][i] % 1234567;
			}

			System.out.println(answer % 1234567);

		}

	}

}
