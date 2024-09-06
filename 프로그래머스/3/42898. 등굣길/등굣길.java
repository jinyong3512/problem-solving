class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] dp = new int[n+1][m+1];
        
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ;j++){
                
                boolean can = true;
                for(int k = 0 ; k < puddles.length ; k++){
                    if(puddles[k][0] == j && puddles[k][1] == i)
                        can = false;
                }
                
                if(!can)
                    continue;
                
                if(i==1 && j==1)
                    dp[1][1] =1;
                else if (i == 1){
                    dp[i][j] = dp[i][j-1];
                }else if (j == 1){
                    dp[i][j] = dp[i-1][j];
                }else
                    dp[i][j] = (dp[i-1][j]+dp[i][j-1])%1000000007;
            }
        }
        
        return dp[n][m];
    }
}