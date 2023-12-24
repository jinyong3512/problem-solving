import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 14:50 도전

        int N;
        int M;
        boolean[] vip;

        N = Integer.parseInt(br.readLine());
        vip = new boolean[N + 1];

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int number = Integer.parseInt(br.readLine());
            vip[number] = true;
        }

        //////////////////////////////////////////////////////////////////////////

//        1명 -> 1개
//        2명 -> 2개
//        3명 -> 3개
//        4명 -> 5개

        int[] dp = new int[41];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
//        dp[3] = 3;
//        dp[4] = 5;
        for (int i = 3; i <= 40; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (vip[i]) {
                if (count != 0) {
                    answer *= dp[count];
                    count = 0;
                }
            } else {
                count++;
            }
        }

        if (count != 0) {
            answer *= dp[count];
            count = 0;
        }

        System.out.println(answer);


    }
}