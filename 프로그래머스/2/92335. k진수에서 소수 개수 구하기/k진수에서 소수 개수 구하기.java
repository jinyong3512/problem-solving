import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        long sum = 0;
        int depth = 0;
        while (n != 0) {
            
            // System.out.printf("n = %d ", n);
            // System.out.printf("n  k = %d ", n % k);
            
            if (n % k == 0) {
                // System.out.printf("sum = %d ", sum);
                if (isPrimeNumber(sum)) {
                    answer++;
                    // System.out.printf("answer = %d ", answer);
                }
                sum = 0;
                depth = 0;
            } else {
                depth++;
                sum += Math.pow(10, depth - 1) * (n % k);
            }
            
            // System.out.println();
            
            n /= k;
        }
        
        if (isPrimeNumber(sum)) {
           answer++; 
        }
        
        return answer;
    }
    
    private boolean isPrimeNumber(long n) {
        if (n == 0 || n == 1) {
            return false;
        }
        
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}