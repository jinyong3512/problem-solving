import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 0으로 시작 X
        // 1이 두 번 연속으로 나타나지 않는다. 즉 11을 부분 문자열로 갖지 않는다.

        // N자리 이친수의 개수

        // N: 1~90

        // dp[i][0]: 끝자리가 0인 갯수
        // dp[i][1]: 끝자리가 1인 갯수

        // dp[i][0] = dp[i-1][0] + dp[i-1][1];
        // dp[i][1] = dp[i-1][0];

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+1][2];
        dp[1][0] = 0;
        dp[1][1] = 1;

        for(int i = 2 ; i <= N ; i++){
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[N][0]+dp[N][1]);


    }
}