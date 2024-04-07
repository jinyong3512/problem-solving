import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, k;
        int[] numbers;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        numbers = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = Integer.parseInt(br.readLine());

        /////////////////////////////////////////////////

        // k원을 만드는 경우의 수
        // 동전의 가치는 10만 10^5
        // k 는 1만

        long[] dp = new long[k + 1];

        // 동전 하나씩
        for (int number : numbers) {
            // i는 가치
            for (int i = 1; i <= k; i++) {

                if (i - number == 0)
                    dp[i]++;

                else if (i - number < 0)
                    continue;

                else {

                    if (dp[i - number] != 0)
                        dp[i] += dp[i - number];
                    else
                        continue;
                }
            }
        }

        System.out.println(dp[k]);


    }
}
