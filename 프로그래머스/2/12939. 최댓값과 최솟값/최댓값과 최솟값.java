import java.util.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            
            long cur = Long.parseLong(str);
            min = Math.min(min, cur);
            max = Math.max(max, cur);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(max);
        
        return sb.toString();
    }
}