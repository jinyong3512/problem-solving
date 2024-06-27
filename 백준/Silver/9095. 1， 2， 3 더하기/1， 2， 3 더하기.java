import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // dp[i] i을 1, 2, 3의 합으로 나타내는 방법의 수

        // dp[1] = 1;
        // dp[2] = 2;
        // dp[3] = 4;
        // dp[4] = 7;

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4 ; i < 11 ; i++)
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];


        int T =  Integer.parseInt(br.readLine());

        for(int t= 0 ; t < T ; t++){
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[n]).append("\n");

        }

        System.out.println(sb);
    }
}
