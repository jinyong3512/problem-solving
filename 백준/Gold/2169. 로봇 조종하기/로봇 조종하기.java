import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{1, 0, 0};
    public static int[] dx = new int[]{0, -1, 1};

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

        int[][][] dp = new int[N][M][3];
        for(int i = 0 ; i < N ; i++){
            for(int j =0 ; j < M ; j++){
                for(int k =0 ; k < 3; k++)
                    dp[i][j][k] = Integer.MIN_VALUE;
            }
        }

        dp[0][0][0] = arr[0][0];
        for(int j = 1; j  < M ; j++){
            dp[0][j][0] = dp[0][j-1][0] + arr[0][j];
        }

        for(int i = 1; i < N ; i++){
            // 왼쪽에서 오른쪽으로 갈꺼임
            dp[i][0][1] = dp[i-1][0][0] + arr[i][0];

            for(int j = 1 ; j < M ; j++) {
                dp[i][j][1] = Math.max(dp[i][j-1][1] + arr[i][j], dp[i - 1][j][0] + arr[i][j]);
            }

            // 오른쪽에서 왼쪽으로 갈꺼임
            dp[i][M-1][2] = dp[i-1][M-1][0] + arr[i][M-1];

            for(int j = M-2 ; j >= 0 ; j--) {
                dp[i][j][2] = Math.max(dp[i][j+1][2] + arr[i][j], dp[i - 1][j][0] + arr[i][j]);
            }

            for(int j =0 ; j < M ; j ++){
                dp[i][j][0] = Math.max(dp[i][j][1],dp[i][j][2]);
            }
        }

        System.out.println(dp[N-1][M-1][0]);



    }
}