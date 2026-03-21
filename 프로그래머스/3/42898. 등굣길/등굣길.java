class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        boolean[][] waters = new boolean[n][m];
        for (int i = 0; i < puddles.length; i++) {
            waters[puddles[i][1] - 1][puddles[i][0] - 1] = true;
        }
        
        int[][] dp = new int[n][m];
        
        dp[0][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            if (waters[i][0]) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        
        for (int j = 1; j < dp[0].length; j++) {
            if (waters[0][j]) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (waters[i][j]) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
                }
            }
        }
        
        // for (int i = 0; i < dp.length; i++) {
        //     for (int j = 0; j < dp[i].length; j++) {
        //         System.out.printf("%d ", dp[i][j]);
        //     }
        //     System.out.println();
        // }
        
        return dp[n - 1][m - 1];
    }
}