import java.util.*;

class Solution {
    int solution(int[][] land) {

        // 원래는 4 * 3^100,000 - 1로 풀리는 문제
        // dp로 풀면 100,000 * 4 * 4;
        
        int[][] dp = new int[land.length][land[0].length];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < 4; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < 4; k++) {
                    if (j == k) {
                        continue;
                    }
                    max = Math.max(max, dp[i - 1][k]);
                }
                
                dp[i][j] = max + land[i][j];
            }
        }
        int answer = Integer.MIN_VALUE;
        for (int j = 0; j < dp[dp.length - 1].length; j++) {
            answer = Math.max(answer, dp[dp.length - 1][j]);
        }
        return answer;
    }
}