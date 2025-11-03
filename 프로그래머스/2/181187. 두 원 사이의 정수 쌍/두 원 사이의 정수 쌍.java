import java.util.*;
import java.math.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = r2 * 4 + 1 - (r1 * 4 + 1);
        
        for (int i = 1; i < r2; i++) {
            
            int x1 = (int)(Math.pow(Math.pow(r1, 2) - Math.pow(i, 2), 0.5));
            int x2 = (int)(Math.pow(Math.pow(r2, 2) - Math.pow(i, 2), 0.5));
                       
            if (Math.pow(x1, 2) + Math.pow(i, 2) == Math.pow(r1, 2)) {
                answer += (x2 - x1 + 1) * 4;
            } else {
                answer += (x2 - x1) * 4;
            }
        }
        
        return answer;
    }
}