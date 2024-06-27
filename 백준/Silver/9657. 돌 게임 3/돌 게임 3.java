import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // i value
        // 1 true
        // 2 false
        // 3 true
        // 4 true
        // 5 true
        // 6 true
        // 7

        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[N + 1];
        dp[1] = true;

        if (N >= 2)
            dp[2] = false;
        if (N >= 3)
            dp[3] = true;
        if (N >= 4)
            dp[4] = true;

        for (int i = 5; i <= N; i++) {

            if (dp[i - 1] && dp[i - 3] && dp[i - 4]) {
                dp[i] = false;
            } else {
                dp[i] = true;
            }
        }

        if (dp[N])
            System.out.println("SK");
        else
            System.out.println("CY");

    }
}
