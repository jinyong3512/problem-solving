import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        HashMap<Character, Integer> hashMap = new HashMap<>();
        
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                int curValue = hashMap.getOrDefault(keymap[i].charAt(j), Integer.MAX_VALUE);
                if (curValue > j)
                    hashMap.put(keymap[i].charAt(j), j);
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            int curCount = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                if (!hashMap.containsKey(targets[i].charAt(j))) {
                    curCount = -1;
                    break;
                } else {
                    curCount += hashMap.get(targets[i].charAt(j)) + 1;
                }                    
            }
            answer[i] = curCount;
        }
        
        return answer;
    }
}