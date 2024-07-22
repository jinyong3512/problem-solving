import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 최대 공부시간 | 과목 수
        // 중요도 | 공부시간

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] datas = new int[K + 1][2];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            datas[i][0] = Integer.parseInt(st.nextToken());
            datas[i][1] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////

        long[][] dp = new long[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {

                // 선택 할 수 있다면
                if (j >= datas[i][1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - datas[i][1]] + datas[i][0]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }


            }

        }

        System.out.println(dp[K][N]);

    }
}
