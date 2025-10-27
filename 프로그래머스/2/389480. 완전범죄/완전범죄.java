import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;
        
        for (int[] inf: info) {
            boolean[][] newDp = new boolean[n][m];
            
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (dp[i][j]) {
                        if (i + inf[0] < n)
                            newDp[i + inf[0]][j] = true;
                        if (j + inf[1] < m)
                            newDp[i][j + inf[1]] = true;
                    }
                }
            }
            
            dp = newDp;            
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m ; j++) {
                if (dp[i][j])
                    return i;
            }
        }
        
        return -1;
        
    }   
}