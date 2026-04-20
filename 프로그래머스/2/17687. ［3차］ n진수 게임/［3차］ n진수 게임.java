import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        
        int number = -1;
        int index = -1;
        StringBuilder answerSb = new StringBuilder();
        
        while(true) {
            number++;                        
            // System.out.printf("number = %d\n", number);
            
            String numberStr = makeStr(number, n);            
            // System.out.printf("numberStr = %s\n", numberStr);
            
            for (int i = 0; i < numberStr.length(); i++) {
                index++;
                index = index % m;
                // System.out.printf("index = %d\n", index);
                
                if (index == p - 1) {
                    answerSb.append(numberStr.charAt(i));
                    // System.out.printf("answerSb.toString() = %s\n", answerSb.toString());
                    if (answerSb.length() >= t) {
                       break;
                    }
                }
            }            
            // System.out.println("-----");
            
            if (answerSb.length() >= t) {
                break;
            }
        }
                
        return answerSb.toString();
    }
    
    private String makeStr(int number, int n) {
        if (number == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            if (number % n >= 10) {
                sb.append((char) ('A' + number % n - 10));
            } else {
                sb.append(number % n);    
            }
            
            number = number / n;
        }
        
        StringBuilder sb2 = new StringBuilder();
        for (int i = sb.length() - 1 ; i >= 0; i--) {
            sb2.append(sb.charAt(i));
        }
        
        return sb2.toString();
    }
}