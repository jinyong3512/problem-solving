import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());

        ///////////////////////////////////////////////////////////////////

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[1] = 0;

        int[] prev = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            prev[i] = i - 1;

            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                prev[i] = i / 2;
            }
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                prev[i] = i / 3;
            }
        }

        sb.append(dp[N]).append("\n");

        int value = N;
        sb.append(value).append(" ");
        while (value != 1) {
            value = prev[value];
            sb.append(value).append(" ");
        }

        System.out.println(sb);

    }
}