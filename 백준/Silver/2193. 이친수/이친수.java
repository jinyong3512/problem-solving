import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());

        ////////////////////////////////////////////////////////////////////////

        // N은 1 ~ 90

        // 0으로 시작하지 않는다.
        // 1이 두번 연속으로 나타나지 않음

        // 1
        // 10
        // 100 101
        // 1000 1001 1010

        // dp[i][0] => 0으로 끝난 갯수
        // dp[i][1] => 1으로 끝난 갯수

        long[][] dp = new long[N + 1][2];
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 1; i < N; i++) {
            // 0으로 끝난 거엔 0,1 붙일 수 있음
            // 1으로 끝난 건엔 0밖에 못 붙임

            dp[i+1][1] = dp[i][0];

            dp[i+1][0] = dp[i][0] + dp[i][1];

        }

        System.out.println(dp[N][0]+dp[N][1]);



    }
}