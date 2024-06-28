import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = arr[i];
            } else {
                dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            }
        }

        int answer = Integer.MIN_VALUE;
        for(int i =0 ; i < n ; i++)
            answer = Math.max(answer,dp[i]);
        System.out.println(answer);

    }
}