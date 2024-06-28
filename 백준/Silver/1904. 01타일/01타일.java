import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 1 or 00

        // 1 2 3 5

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        for(int i =1 ; i <= N ; i++){
            if(i==1)
                dp[i] = 1;
            else if (i==2)
                dp[i] = 2;
            else
                dp[i] = (dp[i-1] + dp[i-2])%15746;
        }
        
        System.out.println(dp[N]);



    }
}