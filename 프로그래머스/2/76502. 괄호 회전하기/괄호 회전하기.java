import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int startIndex = i;
            
            Deque<Character> deque = new ArrayDeque<>();
            
            for (int j = 0; j < s.length(); j++) {
                char cur = s.charAt((startIndex + j) % s.length());
                
                if (deque.isEmpty()) {
                    deque.addLast(cur);
                } else {
                   if (cur == ')' && deque.peekLast() == '(') {
                       deque.removeLast();
                   } else if (cur == '}' && deque.peekLast() == '{') {
                       deque.removeLast();
                   } else if (cur == ']' && deque.peekLast() == '[') {
                       deque.removeLast();
                   } else {
                       deque.addLast(cur);
                   }
                }
            }
            
            if (deque.isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}