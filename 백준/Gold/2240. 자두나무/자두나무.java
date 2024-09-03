import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] arr = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ////////////////////////////////////////////

        // T 10^3
        // W 30

        // 자두의 선택은 움직인다와 안움직인다가 있어
        // 2^30 은 10억임

        // dp적으로 생각을 해보자

        // dp[T][W][1] // T초일때 W번 사용했음 1번 위치
        // dp[T][W][1] -> dp[T-1][W][1] or dp[T-1][W-1][2];

        int[][][] dp = new int[T + 1][W + 1][3];

        for (int i = 0; i <= T; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j][1] = -1;
                dp[i][j][2] = -1;
            }
        }

        if (arr[1] == 1) {
            dp[1][0][1] = 1;
            dp[1][1][2] = 0;
        } else {
            dp[1][0][1] = 0;
            dp[1][1][2] = 1;
        }

        for (int i = 2; i <= T; i++) {

            dp[i][0][1] = dp[i - 1][0][1];
            if (arr[i] == 1)
                dp[i][0][1]++;
            dp[i][0][2] = -1;

            for (int j = 1; j <= W; j++) {

                if (dp[i - 1][j][1] == -1 && dp[i - 1][j - 1][2] == -1) {
                    dp[i][j][1] = -1;
                } else if (dp[i - 1][j][1] == -1) {
                    dp[i][j][1] = dp[i - 1][j - 1][2];
                    if (arr[i] == 1)
                        dp[i][j][1]++;
                } else if (dp[i - 1][j - 1][2] == -1) {
                    dp[i][j][1] = dp[i - 1][j][1];
                    if (arr[i] == 1)
                        dp[i][j][1]++;
                } else {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][2]);
                    if (arr[i] == 1)
                        dp[i][j][1]++;
                }

                if (dp[i - 1][j][2] == -1 && dp[i - 1][j - 1][1] == -1) {
                    dp[i][j][2] = -1;
                } else if (dp[i - 1][j][2] == -1) {
                    dp[i][j][2] = dp[i - 1][j - 1][1];
                    if (arr[i] == 2)
                        dp[i][j][2]++;
                } else if (dp[i - 1][j - 1][1] == -1) {
                    dp[i][j][2] = dp[i - 1][j][2];
                    if (arr[i] == 2)
                        dp[i][j][2]++;
                } else {
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][j - 1][1]);
                    if (arr[i] == 2)
                        dp[i][j][2]++;
                }
            }
        }

        int answer = 0;

        for(int j = 0 ; j <= W ; j++){
            answer = Math.max(Math.max(answer,dp[T][j][1]),dp[T][j][2]);
        }

        System.out.println(answer);


    }
}