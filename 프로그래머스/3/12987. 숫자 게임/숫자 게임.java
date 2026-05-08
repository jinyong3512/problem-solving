import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < B.length; i++) {
            pQ.add(B[i]);
        }
        
        for (int i = 0; i < A.length; i++) {
            while (!pQ.isEmpty() && pQ.peek() <= A[i]) {
                pQ.remove();
            }
            
            if (pQ.isEmpty()) {
                break;
            } else {                
                answer++;
                pQ.remove();
            }
        }
        
        
        return answer;
    }
}