import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            
            int[] tmpArray = new int[commands[i][1] - commands[i][0] + 1];
            for (int j = 0; j < tmpArray.length; j++)
                tmpArray[j] = array[j + commands[i][0] - 1];
            
            // for (int j = 0; j < tmpArray.length; j++)
            //     System.out.printf("%d ", tmpArray[j]);
            // System.out.println();
            
            Arrays.sort(tmpArray);
            
            answer[i] = tmpArray[commands[i][2] - 1];
        }
        
        return answer;
    }
}