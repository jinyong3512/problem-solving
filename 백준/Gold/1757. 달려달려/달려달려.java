import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        /////////////////////////////////////////////

        int[][] dp = new int[N + 1][M + 1];
        dp[1][0] = 0;
        dp[1][1] = arr[1];

        for (int i = 2; i <= N; i++) {

            dp[i][0] = Math.max(dp[i][0], Math.max(dp[i - 1][0], dp[i - 1][1]));

            for (int j = 1; j <= M; j++) {

                dp[i][j] = dp[i - 1][j - 1] + arr[i];
                if (i + j <= N)
                    dp[i + j][0] = Math.max(dp[i + j][0], dp[i][j]);

            }
        }

//        for (int j = 0; j <= M; j++) {
//            for (int i = 1; i <= N; i++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        
        System.out.println(dp[N][0]);

    }
}