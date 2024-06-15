import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;

        n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        for(int i =0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j <= i ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ///////////////////////////////////////////////////////

        long[][] dp = new long[n][n];

        dp[0][0] = arr[0][0];

        for(int i =1 ; i < n ; i++){

            dp[i][0] = dp[i-1][0] + arr[i][0];
            for(int j = 1 ; j  < i ; j ++){
                dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j])+ arr[i][j];
            }
            dp[i][i] = dp[i-1][i-1] + arr[i][i];
        }

        long answer = 0;

        for(int j =0 ; j < n ; j++)
            answer = Math.max(answer,dp[n-1][j]);
        System.out.println(answer);
    }

}