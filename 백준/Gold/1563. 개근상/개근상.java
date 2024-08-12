import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ////////////////////////////////////////

        int[][][] dp = new int[N + 1][2][3];

        // 날짜, 지각, 연속 결석

        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][0][2] = 0;

        dp[1][1][0] = 1;
        dp[1][1][1] = 0;
        dp[1][1][2] = 0;

        for (int i = 2; i <= N; i++) {

            // 지각 0회, 연속 결석 0회
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2])%1000000;

            // 지각 0회, 연속 결석 1회
            dp[i][0][1] = dp[i-1][0][0];

            // 지각 0회, 연속 결석 2회
            dp[i][0][2] = dp[i-1][0][1];

            // 지각 1회, 연속 결석 0회
            dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2] + dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2])%1000000;

            // 지각 1회, 연속 결석 1회
            dp[i][1][1] = dp[i-1][1][0];

            // 지각 1회, 연속 결석 2회
            dp[i][1][2] = dp[i-1][1][1];
        }

        int answer = (dp[N][0][0] + dp[N][0][1] + dp[N][0][2] + dp[N][1][0] + dp[N][1][1] + dp[N][1][2])%1000000;

        System.out.println(answer);

    }
}
