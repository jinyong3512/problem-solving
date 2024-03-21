import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 2 ~ 1000

        int N;
        int[][] costs;

        N = Integer.parseInt(br.readLine());
        costs = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////

        int answer = Integer.MAX_VALUE;

        // 0번 출발 했다고 가정
        long[][] dp = new long[N][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = Integer.MAX_VALUE;
        dp[0][2] = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        answer = Math.min(answer, (int) Math.min(dp[N - 1][1], dp[N - 1][2]));


        // 1번 출발 했다고 가정
        dp = new long[N][3];

        dp[0][0] = Integer.MAX_VALUE;
        dp[0][1] = costs[0][1];
        dp[0][2] = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        answer = Math.min(answer, (int) Math.min(dp[N - 1][0], dp[N - 1][2]));


        // 2번 출발 했다고 가정
        dp = new long[N][3];

        dp[0][0] = Integer.MAX_VALUE;
        dp[0][1] = Integer.MAX_VALUE;
        dp[0][2] = costs[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        answer = Math.min(answer, (int) Math.min(dp[N - 1][0], dp[N - 1][1]));


        System.out.println(answer);


    }
}
