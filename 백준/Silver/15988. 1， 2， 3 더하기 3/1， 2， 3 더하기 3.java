import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        // n은 양수 1,000,000 보다 작거나 같다. 10^6

        long[] dp = new long[1000001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        ////////////////////////////////////////////////////////////////////////

        int T;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N;

            N = Integer.parseInt(br.readLine());

            // 1
            // 1+1 2
            // 1+2 , 1+1+1 , 2+1 , 3
            // 1+3 , 1+1+2, 2+2, 1+2+1 1+1+1+1 2+1+1 3+1

            if (dp[N] == 0) {
                for (int i = 4; i <= N; i++) {
                    dp[i] = (((dp[i - 1] + dp[i - 2]) % 1000000009) + dp[i - 3]) % 1000000009;
                }
            }


            sb.append(dp[N]).append("\n");


        }

        System.out.println(sb);


    }
}