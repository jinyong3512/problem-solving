import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // B -> BA
        // A -> B

        // 초기엔 A만 표시

        int K = Integer.parseInt(br.readLine());
        int[][] dp = new int[K + 1][2];

        dp[0][0] = 1;

        for (int i = 1; i <= K; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][1] = dp[i - 1][0] + dp[i - 1][1];
        }

        System.out.println(dp[K][0] + " " + dp[K][1]);
    }
}