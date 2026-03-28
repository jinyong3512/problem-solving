class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int tmp = n;
        int countOne = 0;
        while (tmp != 0) {
            if (tmp % 2 == 1) {
                countOne++;
            }
            tmp /= 2;
        }
        
        int depth = 0;
        while (true) {            
            depth++;
            tmp = n + depth;

            int countOne2 = 0;
            while (tmp != 0) {
                if (tmp % 2 == 1) {
                    countOne2++;
                }
                tmp /= 2;
            }
            
            if (countOne == countOne2) {
                break;
            }            
        }
        
        return n + depth;
    }
}