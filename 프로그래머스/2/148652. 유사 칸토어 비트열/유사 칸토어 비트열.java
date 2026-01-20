import java.math.*;

class Solution {
    public int solution(int n, long l, long r) {
        
        // 0번째 -> 1
        // 1번째 -> 1 1 0 1 1 -> 5
        // 2번째 -> 11011 11011 00000 11011 11011 -> 25
        
        int zeroCount = 0;
        
        for (long i = l - 1; i <= r - 1; i++) {
            
            boolean flag = false;
            long tmp = i;
            
            for (int j = n; j >= 1; j--) {
                
                if (tmp % 5 == 2) {
                    flag = true;
                    break;
                } else {
                    tmp /= 5;
                }
                
            }        
            
            if (flag)
                zeroCount++;
        }
        
        System.out.println(zeroCount);
        
        return (int)(r - l + 1 - zeroCount);
    }
}