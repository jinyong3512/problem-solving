import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 0 ~ 40

        int[][] dp = new int[41][2];

        dp[0][0] = 1;
        dp[0][1] = 0;

        dp[1][0] = 0;
        dp[1][1] = 1;

        for(int i = 2;  i < 41 ; i ++){
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        int T = Integer.parseInt(br.readLine());
        for(int t = 0 ; t< T; t++){
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N][0]+" "+dp[N][1]+"\n");
        }
        System.out.println(sb);
    }
}
