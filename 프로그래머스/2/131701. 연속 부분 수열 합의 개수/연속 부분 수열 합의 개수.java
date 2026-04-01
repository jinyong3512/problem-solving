import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Map<Integer, Boolean> map = new HashMap<>();
        
        for (int i = 1; i <= elements.length; i++) {
            int left = 0;
            int right = 0;
            
            int sum = 0;
            for (; right < i; right++) {
                sum += elements[right];
            }
            right--;
            
            // left 0
            // right 0
            
            while (left < elements.length) {
                sum -= elements[left];
                left++;
                
                right++;
                if (right == elements.length) {
                    right = 0;
                }                
                sum += elements[right];
                
                map.put(sum, true);
            }        
        }
        
        return map.size();
    }
}