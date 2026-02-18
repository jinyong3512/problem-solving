import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], true);
        }
        
        answer = Math.min(nums.length / 2, hashMap.size());
        
        return answer;
    }
}