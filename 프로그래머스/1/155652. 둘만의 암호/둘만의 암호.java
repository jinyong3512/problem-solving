import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        HashMap <Character, Boolean> hashMap = new HashMap<>();
        
        for (int i = 0; i < skip.length(); i++) {
            hashMap.put(skip.charAt(i), true);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            
            for (int j = 0; j < index; j++) {
                curChar++;
                if (curChar > 'z')
                    curChar -= 26;
                
                if (hashMap.containsKey(curChar)) {
                    j--;
                }
            }
            
            sb.append(curChar);
        }
        
        return sb.toString();
    }
}