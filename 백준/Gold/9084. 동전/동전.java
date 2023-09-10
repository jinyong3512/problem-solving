import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N;
			int[] arr;
			int M;

			N = Integer.parseInt(br.readLine());
			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			M = Integer.parseInt(br.readLine());

			/////////////////////////////////////////////////

			int[] dp = new int[M + 1];
			for (int i = 0; i < arr.length; i++) {
				if (i == 0) {
					for (int j = 1; j <= M; j++) {
						if (j % arr[i] == 0)
							dp[j]++;
					}
				} else {
					for (int j = 0; j <= M; j++) {
						if (j - arr[i] > 0)
							dp[j] = dp[j] + dp[j - arr[i]];
						if (j - arr[i] == 0)
							dp[j]++;
					}
				}
			}

			System.out.println(dp[dp.length-1]);

		}
	}
}
