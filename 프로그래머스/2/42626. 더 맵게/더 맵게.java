import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
                
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        
        for (int i = 0; i < scoville.length; i++) {
            pQ.add(scoville[i]);
        }
        
        int answer = 0;
        
        while (pQ.size() > 1 && pQ.peek() < K) {
            answer++;
            
            int first = pQ.remove();
            int second = pQ.remove();
            
            pQ.add(first + second * 2);
        }
        
        if (pQ.size() == 1 && pQ.peek() < K) {
            return -1;
        }
        
        return answer;
    }
}