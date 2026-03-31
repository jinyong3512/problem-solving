import java.util.*;

class Solution {
    public int solution(int[] arr) {
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            Map<Integer, Integer> tmpMap = new HashMap<>();
                        
            for (int j = 2; j <= arr[i]; j++) {
                // 나누어 떨어지면
                if (arr[i] % j == 0) {
                    tmpMap.put(j, tmpMap.getOrDefault(j, 0) + 1);
                    arr[i] = arr[i] / j;
                    j--;
                }
            }
            
            for (int key: tmpMap.keySet()) {
                if (map.containsKey(key)) {
                    if (map.get(key) < tmpMap.get(key)) {
                        map.put(key, tmpMap.get(key));
                    }
                } else {
                    map.put(key, tmpMap.get(key));
                }                
            }            
        }
        
        int answer = 1;
        
        for (int key: map.keySet()) {
            answer *= Math.pow(key, map.get(key));
        }
        
        return answer;
    }
}