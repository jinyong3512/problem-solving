import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());

        int[] T;
        int[] P;

        T = new int[N + 2];
        P = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N + 2];

        long max = 0;

        for (int i = 1; i <= N + 1; i++) {

            max = Math.max(max, dp[i]);

            if (i + T[i] <= N + 1)
                dp[i + T[i]] = Math.max(dp[i + T[i]], max + P[i]);
        }

        System.out.println(max);

    }
}