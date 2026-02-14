class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {};
        
        int bottom = denom1 * denom2;
        int top = numer1 * denom2 + numer2 * denom1;
        
        for (int i = top; i >= 2; i--) {
            if (bottom % i == 0 && top % i == 0) {
                bottom /= i;
                top /= i;
            }
                
        }
        
        
        return new int[]{top, bottom};
    }
}