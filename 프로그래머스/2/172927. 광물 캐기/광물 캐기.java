import java.util.*;

class Solution {
    
    public static int answer = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        recursion(picks[0], picks[1], picks[2], minerals, 0, 0);
        
        return answer;
    }
    
    public void recursion(int a, int b, int c, String[] minerals, int index, int tmpAnswer) {
        if (index >= minerals.length) {
            answer = Math.min(answer, tmpAnswer);
            return;
        }
        
        if (a == 0 && b == 0 && c == 0) {
            answer = Math.min(answer, tmpAnswer);
            return;
        } 
        
        // a 
        int tmpAnswer2 = tmpAnswer;
        if (a > 0) {
            for (int i = index; i < index + 5; i++) {
                if (i == minerals.length)
                    break;
                tmpAnswer2 += calculate(0, minerals[i]);
            }
            recursion(a - 1, b, c, minerals, index + 5, tmpAnswer2);
        }
        
        // b
        tmpAnswer2 = tmpAnswer;
        if (b > 0) {
            for (int i = index; i < index + 5; i++) {
                if (i == minerals.length)
                    break;
                tmpAnswer2 += calculate(1, minerals[i]);
            }
            recursion(a, b - 1, c, minerals, index + 5, tmpAnswer2);
        }
        
        // c
        tmpAnswer2 = tmpAnswer;
        if (c > 0) {
            for (int i = index; i < index + 5; i++) {
                if (i == minerals.length)
                    break;
                tmpAnswer2 += calculate(2, minerals[i]);
            }
            recursion(a, b, c - 1, minerals, index + 5, tmpAnswer2);
        }
    }
    
    public int calculate(int kok, String mineral) {
        if (kok == 0) {
            return 1;
        } else if (kok == 1) {
            if (mineral.equals("diamond"))
                return 5;
            return 1;
        } else if (kok == 2) {
            if (mineral.equals("diamond"))
                return 25;
            if (mineral.equals("iron"))
                return 5;
            if (mineral.equals("stone"))
                return 1;
        }
        return Integer.MIN_VALUE;
    }
}
