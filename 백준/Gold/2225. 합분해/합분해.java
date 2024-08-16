import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // i 숫자를 만들기 위해서 사용한 갯수 j
        int[][] dp = new int[N + 1][K + 1];

        for(int k = 1 ; k <= K ; k++)
            dp[0][k] = 1;

        for (int number = 1; number <= N; number++) {

            dp[number][1] = 1;

            for (int k = 2; k <= K; k++) {

                for(int t = 0 ; t <= number ; t++){
                    dp[number][k] = (dp[number][k] + dp[t][k-1])%1000000000;
                }

            }
        }

        System.out.println(dp[N][K]);

    }
}
