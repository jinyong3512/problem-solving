import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 비용의 최솟값
        // 0.5 초니까 5000만 초

        // 1번 집 2번 N번 집 과 색 달라
        // N번집 N-1번과 1번집 색 달라야 함
        // 원형이란 얘기임

        // 앞뒤 집의 색과 달라야 함 ㅇㅇ

        // 10^3 *10^3 = 10^6 비용 long 안해도 됌

        int N;
        int[][] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        ////////////////////////////////////////////////

        int answer = Integer.MAX_VALUE;

        long[][] dp = new long[N][3];

        dp[0][0] = arr[0][0];
        dp[0][1] = Integer.MAX_VALUE;
        dp[0][2] = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
        }

        answer = (int) Math.min(answer, dp[N - 1][1]);
        answer = (int) Math.min(answer, dp[N - 1][2]);


        dp = new long[N][3];

        dp[0][0] = Integer.MAX_VALUE;
        dp[0][1] = arr[0][1];
        dp[0][2] = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
        }

        answer = (int) Math.min(answer, dp[N - 1][0]);
        answer = (int) Math.min(answer, dp[N - 1][2]);

        dp = new long[N][3];

        dp[0][0] = Integer.MAX_VALUE;
        dp[0][1] = Integer.MAX_VALUE;
        dp[0][2] = arr[0][2];

        for (int i = 1; i < N ; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
        }

        answer = (int) Math.min(answer, dp[N - 1][0]);
        answer = (int) Math.min(answer, dp[N - 1][1]);


        System.out.println(answer);
    }
}