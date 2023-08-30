import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N, M;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		///////////////////////////////////////////////////

		int[][] dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				dp[i][j] = -1;
		}

		System.out.println(recursion(arr, dp, N - 1, M - 1));

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				System.out.print(dp[i][j] + " ");
//			System.out.println();
//		}

	}

	public static int recursion(int[][] arr, int[][] dp, int i, int j) {
		if (i < 0 || j < 0)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		return dp[i][j] = Math.max(Math.max(recursion(arr, dp, i - 1, j), recursion(arr, dp, i, j - 1)),
				recursion(arr, dp, i - 1, j - 1)) + arr[i][j];

	}

}
