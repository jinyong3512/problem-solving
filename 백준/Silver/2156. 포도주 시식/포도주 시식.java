import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 2 ~ 1000

        int n;
        int[] weights;

        n = Integer.parseInt(br.readLine());
        weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }

        ///////////////////////////////////////

        int[][] dp = new int[n][3];

        dp[0][0] = 0;
        dp[0][1] = weights[0];
        dp[0][2] = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][1] = dp[i - 1][0] + weights[i];
            dp[i][2] = dp[i - 1][1] + weights[i];
        }

        System.out.println(Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]));


    }
}
