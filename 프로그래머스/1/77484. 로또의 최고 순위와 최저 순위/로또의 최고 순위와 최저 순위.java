import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int alreadySameCount = 0;
        for (int i = 0; i < lottos.length; i++) {            
            for (int j = 0; j < win_nums.length; j++) {
                if (lottos[i] == win_nums[j])
                    alreadySameCount++;
            }
        }
        
        int zeroCount = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0)
                zeroCount++;
        }
        
        int maxCount = alreadySameCount + zeroCount; 
        int minCount = alreadySameCount;
        
        int[] ranks = new int[]{6, 6, 5, 4, 3, 2, 1};
        
        return new int[]{ranks[maxCount], ranks[minCount]};
    }
}