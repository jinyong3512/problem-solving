import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 2억 만에 계산 2*10^8
        // 10000은 10^4 * 10^3

        int n;
        int[] arr;

        ///////////////////////////////////////////////////////////////////////

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ///////////////////////////////////////////////////////////////////////

        // dp[i][0] 내껄 선택 안했을 때 최댓값
        // i-1 기준 0 ok 1 ok 2 ok
        // i-2 기준 0 ok 1 ok 2 ok

        // dp[i][1] 내껄 첫번째로 선택 했을때 최댓값
        // i-1 기준 0 ok 1 no 2 no
        // i-2 기준 0 ok 1 ok 2 ok

        // dp[i][2] 내껄 두번째로 선택 했을때 최댓값
        // i-1 기준 0 no 1 ok 2 no
        // i-2 기준 0 no 1 no 2 no

        int[][] dp = new int[n][3];

        dp[0][0] = 0;
        dp[0][1] = arr[0];
        dp[0][2] = arr[0];

        for (int i = 1; i < n; i++) {

            for(int j = i-1 ; j >=0 ; j--){
                dp[i][0] = Math.max(dp[i][0],dp[j][0]);
                dp[i][0] = Math.max(dp[i][0],dp[j][1]);
                dp[i][0] = Math.max(dp[i][0],dp[j][2]);
            }

            dp[i][1] = dp[i-1][0];
            for(int j = i-2 ; j >=0 ; j--){
                dp[i][1] = Math.max(dp[i][1],dp[j][0]);
                dp[i][1] = Math.max(dp[i][1],dp[j][1]);
                dp[i][1] = Math.max(dp[i][1],dp[j][2]);
            }
            dp[i][1] += arr[i];


            dp[i][2] = dp[i-1][1];
            dp[i][2] += arr[i];
        }

        System.out.println(Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]));

    }
}