import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        
        Deque<Integer> deque = new ArrayDeque<>();        
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            while (!deque.isEmpty() && numbers[deque.peekLast()] < numbers[i]) {
                answer[deque.removeLast()] = numbers[i];
            }
            deque.addLast(i);
        }
        
        // for (int i = 0; i < answer.length; i++) {
        //     System.out.printf("%d ", answer[i]);
        // }
        // System.out.println();
        
        while (!deque.isEmpty()) {
            answer[deque.removeLast()] = -1;
        }
        
        return answer;
    }
}