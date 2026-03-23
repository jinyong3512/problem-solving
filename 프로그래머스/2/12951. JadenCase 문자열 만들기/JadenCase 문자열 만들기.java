import java.util.*;

class Solution {
    public String solution(String s) {
        
        StringBuilder tmp = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            
            char cur = s.charAt(i);
            
            if (cur == ' ') {
                if (tmp.length() != 0) {                    
                    if (tmp.charAt(0) >= 'a' && tmp.charAt(0) <= 'z') {
                        answer.append((char)(tmp.charAt(0) + 'A' - 'a'));
                    } else {                        
                        answer.append(tmp.charAt(0));                   
                    }
                    answer.append(tmp.toString().substring(1).toLowerCase());                        
                    tmp.setLength(0);
                }
                answer.append(cur);
            } else {
                tmp.append(cur);                
            }
        }
        if (tmp.length() != 0) {
            if (tmp.charAt(0) >= 'a' && tmp.charAt(0) <= 'z') {
                answer.append((char)(tmp.charAt(0) + 'A' - 'a'));                           
            } else {
                answer.append(tmp.charAt(0));
            }
            answer.append(tmp.toString().substring(1).toLowerCase());
            tmp.setLength(0);
        }
        
        return answer.toString();
    }
}