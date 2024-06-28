import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // n: 1 ~ 1000

        // 1x2 2x1 2x2

        // dp[0] = 0;
        // dp[1] = 1;
        // dp[2] = 3;
        // dp[3] =

        int n = Integer.parseInt(br.readLine());

        // dp[i] = dp[i-1] + 2*dp[i-2];

        int[] dp = new int[n+1];
        dp[0] = 0;

        for(int i = 1 ; i <= n ; i++){

            if(i==1)
                dp[i] = 1;
            else if (i==2)
                dp[i] = 3;
            else
                dp[i] = (dp[i-1] + 2*dp[i-2])%10007;


        }

        System.out.println(dp[n]);

    }
}