import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // dp[i] i개의 카드를 갖기 위해 지불해야 하는 최소 금액
        // dp[i] = Math.min(dp[i-1] + dp[1] , .... m dp[1] + dp[i-1])

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        int[] dp = new int[N+1];

        for(int i = 1 ; i <= N ; i++){
            dp[i] = arr[i];

            for(int j = 1 ; j < i ; j++){
                dp[i] = Math.min(dp[i],dp[i-j]+dp[j]);
            }
        }

        System.out.println(dp[N]);

    }
}