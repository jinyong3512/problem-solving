import java.util.*;

class Solution {
    public int[] solution(String s) {
        
        int changeCount = 0;
        int remove0Count = 0;
        
        while (true) {
            if (s.equals("1")) {
                break;
            }
            
            changeCount++;
            
            int count1 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    count1++;
                } else {
                    remove0Count++;
                }
            }
            
            StringBuilder sb = new StringBuilder();
            while (true) {
                if (count1 == 0) {
                    break;
                }
                
                sb.append(count1 % 2);
                count1 /= 2;                
            }
            
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                sb2.append(sb.charAt(sb.length() - 1 - i));
            }
            
            s = sb2.toString();
        }
                
        return new int[]{changeCount, remove0Count};
    }
}