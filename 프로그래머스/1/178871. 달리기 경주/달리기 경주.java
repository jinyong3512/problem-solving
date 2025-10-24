import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {                
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            hashMap.put(players[i], i);
        }
        
        for (int i = 0; i < callings.length; i++) {
            int backIndex = hashMap.get(callings[i]);
            int frontIndex = backIndex - 1;
            
            String front = players[frontIndex];
            String back = players[backIndex];
            
            players[frontIndex] = back;
            players[backIndex] = front;
            
            hashMap.put(back, frontIndex);
            hashMap.put(front, backIndex);
        }
        
        return players;
    }
}