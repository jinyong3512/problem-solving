import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // -L[i] 체력
        // +J[i] 기쁨

        // 주어진 체력내에서 최대한의 기쁨을 얻기
        // 체력이 0 이나 음수가 되면 안됌

        int N = Integer.parseInt(br.readLine());
        int[] L = new int[N + 1];
        int[] J = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            L[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            J[i] = Integer.parseInt(st.nextToken());

        /////////////////////////////////////////////

        int[][] dp = new int[N + 1][100];
        // 체력이 i일 때 얻을 수 있는 최대 값

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 99; j++) {

                // 넣을 수 있다면
                if (L[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - L[i]] + J[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }

        }

        System.out.println(dp[N][99]);


    }
}