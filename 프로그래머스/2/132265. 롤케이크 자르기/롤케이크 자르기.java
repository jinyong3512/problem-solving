import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();
        for (int i = 0; i < topping.length; i++) {
            bMap.put(topping[i], bMap.getOrDefault(topping[i], 0) + 1);
        }
                
        int answer = 0;
        for (int i = 0; i < topping.length; i++) {
            if (bMap.get(topping[i]) == 1) {
                bMap.remove(topping[i]);
            } else {
                bMap.put(topping[i], bMap.get(topping[i]) - 1);
            }
            
            aMap.put(topping[i], aMap.getOrDefault(topping[i], 0) + 1);
            
            if (aMap.size() == bMap.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}