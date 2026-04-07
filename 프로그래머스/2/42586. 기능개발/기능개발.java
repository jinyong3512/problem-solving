import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> answerList = new ArrayList<>();
                
        for (int i = 0; i < progresses.length; i++) {
            int curDay = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) {
                curDay++;
            }
            
            int j = i + 1;
            for (; j < progresses.length; j++) {
                int curDay2 = (100 - progresses[j]) / speeds[j];
                if ((100 - progresses[j]) % speeds[j] != 0) {
                    curDay2++;
                }               
                
                if (curDay >= curDay2) {
                    continue;
                } else {
                    break;
                }
            }
            
            answerList.add(j - i);
            
            
            i = j - 1;
        }                
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}