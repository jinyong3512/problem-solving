import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N N-1 N-2 ... 3 2 1
        // M개 이하의 도시를 지나려고 함
        // 1에서 시작해서 N에서 끝나야 함
        // 최대한 맛있는 기내식을 먹으며 이동하려고 함

        // N까지 M번 썼을 때 최댓값

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] scores = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int startVertex = Integer.parseInt(st.nextToken());
            int endVertex = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            scores[startVertex][endVertex] = Math.max(scores[startVertex][endVertex], score);
        }

        ////////////////////////////////////////////////////////

        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++)
                dp[i][j] = -1;
        }

        dp[1][1] = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                for (int k = 1; k < M; k++) {
                    if (scores[j][i] > 0 && dp[j][k]!=-1)
                        dp[i][k + 1] = Math.max(dp[i][k + 1], dp[j][k] + scores[j][i]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= M; i++)
            answer = Math.max(answer, dp[N][i]);
        System.out.println(answer);

    }
}
