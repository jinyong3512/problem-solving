import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = n; i >= 1; i--) {
            if (n % 2 == 1) {
                if (i % 2 == 1) {
                    answer += i;
                } else {
                    
                }
            } else {
                if (i % 2 == 1) {
                    
                } else {
                    answer += Math.pow(i,2);
                }
            }
        }
        return answer;
    }
}