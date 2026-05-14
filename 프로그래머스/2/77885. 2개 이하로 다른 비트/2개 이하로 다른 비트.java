import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        // 짝수인 경우
        // 00010 -> 00011
        // 홀수인 경우
        // 00011 -> 00101
        long[] answer = new long[numbers.length];                
        
        for (int i = 0; i < numbers.length; i++) {
            
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
                
            } else {                            
                StringBuilder sb = new StringBuilder();
                while (numbers[i] != 0) {
                    sb.append(numbers[i] % 2);
                    numbers[i] /= 2;
                }            
                sb.append('0');
                sb.reverse();
                
                String str = sb.toString();
                
                // System.out.println(str);
                
                for (int j = str.length() - 1; j >= 0; j--) {
                    if (str.charAt(j) == '0') {
                        if (str.charAt(j + 1) == '0') {
                            str = str.substring(0, j) + "11" + str.substring(j + 2);
                        } else {
                            str = str.substring(0, j) + "10" + str.substring(j + 2);
                        }
                        break;
                    }
                }
                
                // System.out.println(str);
                
                answer[i] = 0L;
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == '1') {
                        answer[i] += Math.pow(2, str.length() - 1 - j);
                    }
                }
            
            }
        }
                
        return answer;
    }
}