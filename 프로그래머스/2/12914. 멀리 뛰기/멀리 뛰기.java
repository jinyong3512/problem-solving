class Solution {
    public long solution(int n) {
        long answer = 0;
        
        // dp[n]을 구하자. n = 4;
        
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }
        
        return dp[n];
    }
}