import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (s / n == 0) {
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < n - s % n) {
                answer[i] = s / n;
            } else {
                answer[i] = s / n + 1;
            }
        }
        
        return answer;
    }
}