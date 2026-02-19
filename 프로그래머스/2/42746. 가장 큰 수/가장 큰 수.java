import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(nums, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                String tmp1 = o1 + o2;
                String tmp2 = o2 + o1;
                if (Integer.parseInt(tmp1) > Integer.parseInt(tmp2)) 
                    return -1;
                else if (Integer.parseInt(tmp1) == Integer.parseInt(tmp2))
                    return 0;
                else
                    return 1;
            }
        });
        
        for (int i = 0; i < nums.length; i++) {
            answer += nums[i];
        }
        
        // 00000000
        int notZeroFirstIndex = -1;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) != '0') {
                notZeroFirstIndex = i;
                break;
            }
        }
        
        if (notZeroFirstIndex == -1) {
            answer = "0";
        } else {
            answer = answer.substring(notZeroFirstIndex, answer.length());
        }
        return answer;
    }
}