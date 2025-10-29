class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        return underBound(diffs, times, limit);
    }
    
    public int underBound(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            long takeTime = times[0];
            
            for (int i = 1; i < diffs.length; i++) {
                int levelGap = diffs[i] - mid;
                
                if (levelGap <= 0) {
                    takeTime += times[i];
                } else {
                    takeTime += levelGap * (times[i] + times[i-1]);
                    takeTime += times[i];
                }
            }
            
            System.out.printf("%d %d %d %d %d\n",left, right, mid, takeTime, limit);
            
            if (takeTime > limit) {
                left = mid + 1;
            } else if (takeTime == limit) {
                right = mid - 1;
                // left = mid + 1;
            } else {
                right = mid - 1;
            }            
            
            
        }
        
        return left;
        
    }
}