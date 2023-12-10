import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[][] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            arr[i][0] = T;
            arr[i][1] = P;
        }

        ///////////////////////////////////////////////////////////////////////////

        // i번째 상담을 무조건 선택 할 때 제일 큰 돈을 넣어라
        int[] dp = new int[N];

        for (int i = 0; i < arr.length; i++) {

            for (int j = i - 1; j >= 0; j--) {
                if (j + arr[j][0] <= i) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }

            if (arr[i][0] + i <= arr.length)
                dp[i] += arr[i][1];
        }

        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            answer = Math.max(answer, dp[i]);
//            System.out.print(dp[i] + " ");
        }

        System.out.println(answer);

    }
}