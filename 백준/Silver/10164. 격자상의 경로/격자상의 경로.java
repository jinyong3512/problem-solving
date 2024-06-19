import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, K;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K == 0) {

            long[][] dp = new long[N][M];

            for (int j = 0; j < M; j++)
                dp[0][j] = 1;
            for (int i = 0; i < N; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            System.out.println(dp[N - 1][M - 1]);


        } else {
            K--;

            int newN = K / M;
            int newM = K % M;

            long[][] dp = new long[newN + 1][newM + 1];

            for (int i = 0; i <= newN; i++)
                dp[i][0] = 1;
            for (int j = 0; j <= newM; j++)
                dp[0][j] = 1;

            for (int i = 1; i <= newN; i++) {
                for (int j = 1; j <= newM; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }

            ///////

            long[][] dp2 = new long[N][M];

            for (int i = newN; i < N; i++)
                dp2[i][newM] = 1;
            for (int j = newM; j < M; j++)
                dp2[newN][j] = 1;

            for (int i = newN + 1; i < N; i++) {
                for (int j = newM + 1; j < M; j++) {
                    dp2[i][j] = dp2[i - 1][j] + dp2[i][j - 1];
                }
            }


            System.out.println(dp[newN][newM] * dp2[N - 1][M - 1]);
        }
    }
}