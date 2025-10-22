class Solution {
    public long solution(int n, int[] times) {        
        return lowerBound(n, times);
    }
    public long lowerBound(int n, int[] times) {
        
        int maxTime = 0;
        for (int time: times)
            maxTime = Math.max(maxTime, time);
        
        long left = 1;
        long right = (long) n * (long) maxTime;
        System.out.println(right);
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            long count = 0;
            
            for (int time: times)
                count += mid / time;
            
            if (count > n) {
                right = mid - 1;
            } else if (count == n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            System.out.printf("%d, %d\n",mid, count);
        }
        
        return left;
    }
}