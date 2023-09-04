import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String str1 = br.readLine();
		String str2 = br.readLine();

		////////////////////////////////////

		int[][] dp = new int[str1.length()][str2.length()];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}

		int answer = Integer.MIN_VALUE;

		recursion(str1, str2, dp, str1.length() - 1, str2.length() - 1);

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				answer = Math.max(answer, dp[i][j]);
			}
		}

		System.out.println(answer);
	}

	public static int recursion(String str1, String str2, int[][] dp, int i, int j) {

		if (i < 0 || j < 0)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		if (str1.charAt(i) == str2.charAt(j))
			return dp[i][j] = recursion(str1, str2, dp, i - 1, j - 1) + 1;
		else
			return dp[i][j] = Math.max(recursion(str1, str2, dp, i - 1, j), recursion(str1, str2, dp, i, j - 1));

	}

}
