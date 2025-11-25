class Solution {
    public int solution(int x, int y, int n) {
        
        int[] count = new int[y + 1];
        
        for (int i = 0; i <= y; i++)
            count[i] = Integer.MAX_VALUE;
        
        count[x] = 0;
        
        for (int i = x + 1; i <= y; i++) {
            if (i - n >= 1 && count[i - n] != Integer.MAX_VALUE) {
                count[i] = Math.min(count[i], count[i - n] + 1);
            }
            if (i % 2 == 0 && count[i / 2] != Integer.MAX_VALUE) {
                count[i] = Math.min(count[i], count[i / 2] + 1);
            }
            if (i % 3 == 0 && count[i / 3] != Integer.MAX_VALUE) {
                count[i] = Math.min(count[i], count[i / 3] + 1);
            }
        }
        
        if (count[y] == Integer.MAX_VALUE)
            return -1;
        else
            return count[y];
        
    }
}