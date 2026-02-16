import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        // int answer = 0;
        
        int[] numbers = new int[]{a, b, c, d};
        Arrays.sort(numbers);
        a = numbers[0];
        b = numbers[1];
        c = numbers[2];
        d = numbers[3];
        
        if (a == b) {
            if (b == c) {
                if (c == d) {
                    // abcd 일치
                    return 1111 * a;
                } else {
                    // abc 일치
                    return (int)Math.pow(10 * a + d, 2);
                }                
            } else {
                if (c == d) {
                    // ab 일치 cd 일치 
                    return (a + c) * (int)Math.abs(a - c);
                } else {
                    // ab 일치
                    return c * d;
                }
            }
        } else {
            if (b == c) {
                if (c == d) {
                    // bcd 일치
                    return (int)Math.pow(10 * b + a, 2);
                } else {
                    // bc 일치
                    return a * d;
                }                
            } else {
                if (c == d) {
                    // cd 일치
                    return a * b;
                } else {
                    // 다 불일치
                    return a;
                }
            }
        }
    }
}