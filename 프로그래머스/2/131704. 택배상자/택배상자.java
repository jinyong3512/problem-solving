import java.util.*;

class Solution {
    public int solution(int[] order) {
        
        // 4 3 1 2 5        
        // stack -> 1 2 3                
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        int n = 1;
        int answer = 0;
        int orderIndex = 0;
        
        
        while (n <= order.length) {
            
            deque.addLast(n);
            
            while (!deque.isEmpty() && deque.peekLast() == order[orderIndex]) {
                answer++;
                orderIndex++;
                deque.removeLast();
            }
            
            n++;
        }
        
        return answer;
    }
}