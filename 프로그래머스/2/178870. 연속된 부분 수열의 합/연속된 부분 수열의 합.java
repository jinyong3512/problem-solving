class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int[] sum = new int[sequence.length + 1];
        sum[0] = 0;
        for (int i = 0; i < sequence.length; i++) {
            sum[i + 1] = sum[i] + sequence[i];
        }
        
        int left = 0;
        int right = 1;
        int answerRight = sum.length - 1;
        int answerLeft = 0;
        
        while (true) {
            int nowK = sum[right] - sum[left];
            
            if (nowK > k) {
                left++;
            } else if (nowK == k) {
                if ( answerRight - answerLeft > right - left ) {
                    answerRight = right;
                    answerLeft = left;
                }
                right++;
            } else {
                right++;
            }
            
            if (left >= sum.length || right >= sum.length)
                break;
        }
        
        return new int[]{answerLeft, answerRight - 1};
    }
}