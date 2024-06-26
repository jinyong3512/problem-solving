import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, k;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n + 1][n + 1];

        dp[1][1] = 1;

        System.out.println(function(n, k, dp));
    }

    public static long function(int n, int k, long[][] dp) {
        if (dp[n][k] != 0)
            return dp[n][k];

        if (k == 1)
            dp[n][k] = 1;
        else if (k == n)
            dp[n][k] = 1;
        else
            dp[n][k] = function(n - 1, k, dp) + function(n - 1, k - 1, dp);

        return dp[n][k];

    }
}