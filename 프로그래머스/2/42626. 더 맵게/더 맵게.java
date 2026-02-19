import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pQ.add(scoville[i]);
        }
        
        while (true) {
            if (pQ.peek() >= K)
                break;
            
            answer++;
            if (pQ.size() > 1) {
                int num1 = pQ.remove();    
                int num2 = pQ.remove();
                pQ.add(num1 + num2 * 2);
                
            } else
                return -1;
        }
        
        return answer;
    }
}