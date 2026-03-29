import java.util.*;

class Solution {
    public int solution(String s) {
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (stack.isEmpty()) {
                stack.addLast(cur);
            } else {
                if (stack.peekLast() == cur) {
                    stack.removeLast();
                } else {
                    stack.addLast(cur);
                }
            }
        }

        if (stack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }
}