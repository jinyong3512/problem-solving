import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] dp = new int[41][2];

        dp[1][0] = 1;
        dp[1][1] = 0;

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < M; i++)
            visited[Integer.parseInt(br.readLine())] = true;

        int answer = 1;

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                if (count != 0) {
                    answer *= (dp[count][0] + dp[count][1]);
                    count = 0;
                }
            } else {
                count++;
            }
        }

        if (count != 0) {
            answer *= (dp[count][0] + dp[count][1]);
            count = 0;
        }


        System.out.println(answer);


    }
}