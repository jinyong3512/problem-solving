import java.util.*;
import java.math.*;

class Solution {
    
    public static int answer1 = 0; 
    public static int answer2 = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        int[] rates = new int[emoticons.length];
        
        combination(users, emoticons, 0, rates);
        
        answer[0] = answer1;
        answer[1] = answer2;
        
        return answer;
    }
    
    public static void combination(int[][] users, int[] emoticons, int emoticonsIndex, int[] rates) {
        if (emoticonsIndex == emoticons.length) {
            
            int tmpAnswer1 = 0;
            int tmpAnswer2 = 0;
            
            for (int i = 0; i < users.length; i++) {
                
                int tmpSum = 0;
                for (int j = 0; j < emoticons.length; j++) {
                    if (users[i][0] <= rates[j]) {
                        // 가격 * (100 - 할인) * 0.01
                        tmpSum += emoticons[j] *  (100 - rates[j]) / 100;
                    }
                }
                
                if (users[i][1] <= tmpSum) {
                    tmpAnswer1++;
                } else {
                    tmpAnswer2 += tmpSum;
                }
            }
            
            if (answer1 < tmpAnswer1) {
                answer1 = tmpAnswer1;
                answer2 = tmpAnswer2;
            } else if (answer1 == tmpAnswer1) {
                if (answer2 < tmpAnswer2) {
                    answer2 = tmpAnswer2;
                }
            }
            
            /*
            for (int i = 0; i < rates.length; i++)
                System.out.printf("%d ", rates[i]);
            System.out.println();
            
            System.out.printf("%d %d\n" ,answer1, answer2);
            */
            
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            rates[emoticonsIndex] = i * 10;
            combination(users, emoticons, emoticonsIndex + 1, rates);
        }
    }
}