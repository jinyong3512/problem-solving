import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] mats, String[][] park) {    
        int maxLength = -1;
        
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length; j++) {
                if (!park[i][j].equals("-1")) {
                    // System.out.printf("%d ", maxLength);
                    continue;
                }
                
                boolean success = true;
                while (success) {
                    for (int y = 0; y < maxLength + 1; y++) {
                        if (i + y >= park.length) {
                            success = false;
                            break;
                        }                        
                        for (int x = 0; x < maxLength + 1; x++) {
                            if (j + x >= park[0].length) {
                                success = false;
                                break;
                            }
                            if (!park[i + y][j + x].equals("-1")){
                                success = false;
                                break;
                            }
                        }
                        if (!success)
                            break;
                    }
                    if (success)
                        maxLength++;
                }
                
                // System.out.printf("%d ", maxLength);
            }
            // System.out.println();
        }
        
        int answer = -1;
        
        for (int value : mats) {
            if (value <= maxLength)
                answer = Math.max(answer, value);
        }
        
        return answer;
    }
}