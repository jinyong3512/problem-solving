import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 증가하는 부분 수열 중에서 합이 가장 큰 것

        // N 10^3
        // number 10^3

        int N;
        int[] A;

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////////////////

        // i를 무조건 선택했을 때 증가하는 부분 수열중 합의 최대
        int[] dp = new int[N];

        dp[0] = A[0];
        for (int i = 1; i < N; i++) {
            dp[i] = A[i];
            for (int j = 0; j < i; j++) {
                // j보다 크다
                if (A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
        }

        int answer = 0;

        for(int i = 0 ; i < N ; i++)
            answer = Math.max(answer,dp[i]);

        System.out.println(answer);

    }
}