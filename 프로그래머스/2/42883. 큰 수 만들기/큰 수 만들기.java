import java.util.*;

class Solution {
    public String solution(String number, int k) {
        
        // 가장 멍청하게 풀면 nCk = n(n-1)... / 1 2 3 시간초과        
        // Stack을 이용하면?                
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < number.length(); i++) {
            int curNumber = number.charAt(i) - '0';
            
            if (deque.isEmpty()) {
                deque.addLast(curNumber);
            } else {
                // 전 것이 더 크면 일단 넣어.
                if (deque.peekLast() > curNumber) {
                    deque.addLast(curNumber);
                } 
                // 전 것이 같아도 일단 넣어.
                else if (deque.peekLast() == curNumber) {
                    deque.addLast(curNumber);
                } 
                // k 다쓰거나 전 것이 크거나 같거나 stack이 빌때까지 빼
                else {
                    
                    while (true) {
                        if (deque.isEmpty()) {
                            break;
                        }
                        
                        if (k == 0) {
                            break;
                        }
                        
                        if (deque.peekLast() >= curNumber) {
                            break;
                        }
                        
                        deque.removeLast();
                        k--;
                    }
                    
                    deque.addLast(curNumber);
                }
            }
        }
        
        // 987654321 케이스를 위한 
        StringBuilder sb = new StringBuilder();
        int size = deque.size();
        
        for (int i = 0; i < size - k; i++) {
            int curNumber = deque.removeFirst();
            
            if (sb.length() == 0 && curNumber == 0) {
                continue;
            } else {
                sb.append(curNumber);   
            }
        }
        
        if (sb.length() == 0)
            sb.append("0");
        
        return sb.toString();
    }
}