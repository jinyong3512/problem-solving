import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{1, 0, 1};
    public static int[] dx = new int[]{0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        long[][] dp = new long[N][M];
        for (int i = 0; i < N; i++) {

            // j =0;
            dp[i][0] = arr[i][0];
            if (i - 1 >= 0)
                dp[i][0] += dp[i - 1][0];

            for (int j = 1; j < M; j++) {

                if(i== 0){
                   dp[i][j] = dp[i][j-1] + arr[i][j];
                }
                else{
                    dp[i][j] = Math.max(Math.max(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) + arr[i][j];
                }

            }
        }

        System.out.println(dp[N-1][M-1]);

    }
}