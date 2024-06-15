import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 모든 계단을 밟으면 최대 이득인거야
        // 하지만 문제 조건에서 연속된 3개의 계단을 밟지 말라고 했어
        // 해당 계단을 건너 뛰어야 이득일까 아닐까 직접 해봐야 알겠지?

        // i번째 계단을 밟았다고 생각했을 때 상태는 2가지가 있어
        // i-1 밟고 온 경우 -> i+2로 가야 함
        // i-2 밟고 온 경우 -> i+1 or i+2로 가야 함

        int N;
        int[] arr;
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        /////////////////////////////////////////////////////////

        long[][] dp = new long[N + 1][2];
        
        dp[1][0] = arr[1];
        dp[1][1] = arr[1];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1] + arr[i];
            if (i - 2 >= 0)
                dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + arr[i];

        }

        System.out.println(Math.max(dp[N][0],dp[N][1]));

    }
}