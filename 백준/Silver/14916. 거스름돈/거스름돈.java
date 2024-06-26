import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;

        n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for (int i = 2; i <= n; i++) {

            if (dp[i - 2] != Integer.MAX_VALUE)
                dp[i] = Math.min(dp[i], dp[i - 2] + 1);
            if (i - 5 >= 0) {
                if (dp[i - 5] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
        }

        if (dp[n] == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(dp[n]);

    }
}