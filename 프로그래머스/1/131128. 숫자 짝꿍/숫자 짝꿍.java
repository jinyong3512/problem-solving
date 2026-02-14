import java.util.*;
import java.math.*;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        
        int[] XNumbers = new int[10];
        int[] YNumbers = new int[10];
        
        for (int i = 0; i < X.length(); i++) {
            XNumbers[X.charAt(i) - '0']++;
        }
        for (int i = 0; i < Y.length(); i++) {
            YNumbers[Y.charAt(i) - '0']++;
        }
        
        for (int i = 9; i >= 0; i--) {
            int commonNumber = Math.min(XNumbers[i], YNumbers[i]);
                        
            for (int j = 0; j < commonNumber; j++) {
                sb.append((char)(i + '0'));
                
                if (i == 0 && sb.length() == 1)
                    break;
            }
            
        }
        
        if (sb.length() == 0)
            sb.append("-1");
        
        return sb.toString();
        
        
    }
}