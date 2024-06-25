import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[][] arr;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        int[][][] dp = new int[N][N][3];

        // 0 가로
        // 1 세로
        // 2 대각선

        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (arr[i][j] == 1)
                    continue;

                if (j - 1 >= 0) {
                    dp[i][j][0] += dp[i][j - 1][0];
                    dp[i][j][0] += dp[i][j - 1][2];
                }

                if (i - 1 >= 0) {
                    dp[i][j][1] += dp[i - 1][j][1];
                    dp[i][j][1] += dp[i - 1][j][2];
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (arr[i - 1][j] == 0 && arr[i][j - 1] == 0) {
                        dp[i][j][2] += dp[i - 1][j - 1][0];
                        dp[i][j][2] += dp[i - 1][j - 1][1];
                        dp[i][j][2] += dp[i - 1][j - 1][2];
                    }
                }

            }
        }

        System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);


    }
}