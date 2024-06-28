import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 2][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        /////////////////////////////////////////////////

        // 바텀업 역순회

//        int[] dp = new int[N + 2];
//
//        for (int i = N; i >= 1; i--) {
//            if (i + arr[i][0] <= N + 1) {
//                dp[i] = Math.max(dp[i + 1], arr[i][1] + dp[i + arr[i][0]]);
//            } else {
//                dp[i] = dp[i + 1];
//            }
//        }
//
//        System.out.println(dp[1]);

        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            if (i + arr[i][0] <= N + 1)
                dp[i+arr[i][0]] = Math.max(dp[i+arr[i][0]], dp[i] + arr[i][1]);

        }
        System.out.println(dp[N+1]);

    }
}