import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        BigInteger[] dp = new BigInteger[n+1];

        for (int i = 0; i <= n; i++) {
            if (i == 0)
                dp[i] = new BigInteger("0");
            else if (i == 1)
                dp[i] = new BigInteger("1");
            else
                dp[i] = dp[i-1].add(dp[i-2]);
        }

        System.out.println(dp[n]);

    }
}