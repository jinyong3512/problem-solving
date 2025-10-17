import java.util.*;
import java.io.*;

class Solution {
    public boolean check (int[] mats, String[][] park, int i, int j, int lastTrySize) {
        for (int y = 0; y < lastTrySize; y++) {
            for (int x = 0; x < lastTrySize; x++) {
                int newY = i + y;
                int newX = j + x;
                if (newY >= park.length || newX >= park[0].length)
                    return false;
                if (!park[newY][newX].equals("-1"))
                    return false;
            }
        }
        return true;
    }
    public int solution(int[] mats, String[][] park) {    
        int lastTrySize = 1;
        
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length; j++) {
                if (!park[i][j].equals("-1")) 
                    continue;                
                
                while (check(mats, park, i, j, lastTrySize))
                    lastTrySize++;                
            }
        }
        
        int answer = -1;
        
        for (int mat : mats) {
            if (mat <= lastTrySize-1)
                answer = Math.max(answer, mat);
        }
        
        return answer;
    }
}