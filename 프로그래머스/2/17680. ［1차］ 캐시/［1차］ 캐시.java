import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < cities.length; i++) {
            
            String city = cities[i].toLowerCase();
            
            boolean find = false;
            Deque<String> newDeque = new ArrayDeque<>();
            
            while (!deque.isEmpty()) {
                if (deque.peekFirst().equals(city)) {
                    find = true;
                    deque.removeFirst();
                } else {
                    newDeque.addLast(deque.removeFirst());
                }
            }
            
            if (find) {
                answer += 1;
            } else {
                answer += 5;                
            }
            
            if (newDeque.size() == cacheSize && !newDeque.isEmpty()) {
                newDeque.removeFirst();
            }            
            if (cacheSize != 0) {
                newDeque.addLast(city);
            }            
            deque = newDeque;
        }
        
        return answer;
    }
}