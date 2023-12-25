import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] P;

        N = Integer.parseInt(br.readLine());
        P = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////////////////////////

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = P[i];
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            }
        }

        System.out.println(dp[N]);

    }
}