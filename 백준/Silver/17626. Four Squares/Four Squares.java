import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // n: 1~5*10^4

        // dp[i]: i라는 숫자를 최소 개수의 제곱수 합으로 표현
        // dp[1]: 1 = 1
        // dp[2]: 1 + 1 = 2
        // dp[3]: 1 + 1 + 1 = 3
        // dp[4]: 2^2 = 1;
        // dp[5]: 2^2 + 1 = 2;
        // dp[6]: dp[5] +1 = 3;
        // dp[7]: dp[6] +1 = 4;
        // dp[8]: dp[7]  + 1 = 5 , dp[4]+dp[4];

        int n = Integer.parseInt(br.readLine());

        ////////////////////////////////////////

        // dp[i] = min(dp[i-j*j])+1;

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[n]);

    }
}
