import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};

        Map<String, Boolean> map = new HashMap<>();
        
        for (int i = 0; i < words.length; i++) {
            
            if (i != 0 && words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                return new int[]{i % n + 1, i / n + 1};
            }
            if (map.containsKey(words[i])) {
                return new int[]{i % n + 1, i / n + 1};
            } else {
                map.put(words[i], true);
            }
            
        }

        return new int[]{0, 0};
    }
}