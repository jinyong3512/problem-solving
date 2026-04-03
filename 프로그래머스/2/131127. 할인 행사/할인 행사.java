import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        Map<String, Integer> cur = new HashMap<>();        
        int left = 0;
        int right = 0;
        for (; right < 10; right++) {
            cur.put(discount[right], cur.getOrDefault(discount[right], 0) + 1);
        }
        right--;
        
        if (isSame(map, cur)) {
            answer++;
        }
        
        while (true) {
            if (cur.get(discount[left]) == 1) {
                cur.remove(discount[left]);
            } else {
                cur.put(discount[left], cur.get(discount[left]) - 1);
            }
            left++;
            
            right++;
            if (right >= discount.length) {
                break;
            }
            cur.put(discount[right], cur.getOrDefault(discount[right], 0) + 1);
            
            if (isSame(map, cur)) {
                answer++;
            }
            
        }
        
        return answer;
    }
    
    private boolean isSame(Map<String, Integer> map1, Map<String, Integer> map2) {
        for (String key1: map1.keySet()) {
            if (map2.containsKey(key1)) {
                if (map1.get(key1) == map2.get(key1)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        for (String key2: map2.keySet()) {
            if (map1.containsKey(key2)) {
                if (map1.get(key2) == map2.get(key2)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }        
        
        return true;
    }
}