class Solution {
    public long solution(int n, int[] times) {
        /// 5556667777 이런식이고 lowerBound 쪽 구하기
        return lowerBound(n, times);
    }
    
    public long lowerBound(int n, int[] times) {
        long left = 1;
        long right = 1000000000L * 1000000000;
        
        while (left < right) {
            // long mid = (left + right) / 2;
            long mid = left + (right - left) / 2;
            
            long p = 0;
            for (int time: times) {
                p += mid / time;
                // mid = Long.MAX_VALUE;
                // time 1 짜리가  100,000명 
            }
            
            if (p > n) {
                right = mid;
            } else if (p == n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}