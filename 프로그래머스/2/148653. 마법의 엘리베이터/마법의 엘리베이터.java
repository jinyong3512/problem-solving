import java.util.*;

class Solution {        
    public int solution(int storey) {
        
        int answer = 0;
        
        while (storey > 0) {
            int remain = storey % 10;
            int remainNext = storey / 10 % 10;
            
            if (remain < 5) {
                answer += remain;
                storey /= 10;
            } else if (remain == 5) {
                if (remainNext >= 5) {
                    answer += 10 - remain;
                    storey /= 10;
                    storey += 1;
                } else {
                    answer += remain;
                    storey /= 10;
                }
            } else {
                answer += 10 - remain;
                storey /= 10;
                storey += 1;
            }
        }
        
        return answer;
    }
}