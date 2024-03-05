import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // n 은 1~10^2
        // k 는 1~10^4
        // 가치는 1~10^5

        int n, k;
        int[] values;
        int[] dp;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        values = new int[n];
        dp = new int[k + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 10001;
        }
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        //////////////////////////////////////////////////////////////

        for (int i = 0; i < values.length; i++) {
            for (int money = 1; money <= k; money++) {

                if (money - values[i] < 0)
                    continue;
                else if (money - values[i] == 0) {
                    dp[money] = 1;
                } else if (money - values[i] > 0) {

                    dp[money] = Math.min(dp[money], dp[money - values[i]] + 1);

                } else {
                    System.out.println("버그");
                }

            }

        }

        if (dp[k] == 10001)
            System.out.println("-1");
        else
            System.out.println(dp[k]);

    }
}