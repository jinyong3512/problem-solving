import java.math.*;

class Solution {
    public int solution(int[][] sizes) {
        
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < sizes.length; i++) {
            num1 = Math.max(num1, Math.max(sizes[i][0], sizes[i][1]));
            num2 = Math.max(num2, Math.min(sizes[i][0], sizes[i][1]));
        }
        
        return num1 * num2;
    }
}