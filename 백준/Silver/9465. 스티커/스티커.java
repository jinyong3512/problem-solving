import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n;
            int[][] arr;

            n = Integer.parseInt(br.readLine());
            arr = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            //////////////////////////////////////////////////////////

            // dp[i][j] i j를 선택 했을 때 j전에 있는 것들 골라서 최댓 값

            int[][] dp = new int[2][n + 1];
            dp[0][0] = 0;
            dp[1][0] = 0;

            dp[0][1] = arr[0][0];
            dp[1][1] = arr[1][0];

            for (int j = 2; j <= n; j++) {

                // 윗 칸일때
                dp[0][j] = Math.max(dp[1][j-1]+arr[0][j-1] , dp[0][j]);
                dp[0][j] = Math.max(dp[0][j-2]+arr[0][j-1] , dp[0][j]);
                dp[0][j] = Math.max(dp[1][j-2]+arr[0][j-1] , dp[0][j]);

                // 아래 칸일때
                dp[1][j] = Math.max(dp[0][j-1]+arr[1][j-1] , dp[1][j]);
                dp[1][j] = Math.max(dp[0][j-2]+arr[1][j-1] , dp[1][j]);
                dp[1][j] = Math.max(dp[1][j-2]+arr[1][j-1] , dp[1][j]);

            }

//            System.out.println("------------------");
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(dp[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("------------------");

            int answer = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= n; j++) {
                    answer = Math.max(answer, dp[i][j]);
                }
            }
            sb.append(answer).append("\n");

        }

        System.out.println(sb);
    }
}