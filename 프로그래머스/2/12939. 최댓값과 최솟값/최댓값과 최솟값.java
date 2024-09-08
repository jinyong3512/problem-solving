import java.util.*;

class Solution {
    public String solution(String s) {
        
        StringTokenizer st = new StringTokenizer(s);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        while(true){
            if(!st.hasMoreTokens())
                break;
            int cur = Integer.parseInt(st.nextToken());
            min = Math.min(min,cur);
            max = Math.max(max,cur);
        }
        
        return String.valueOf(min)+" "+String.valueOf(max);
    }
}