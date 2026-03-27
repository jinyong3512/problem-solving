class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int left = 0;
        int right = 0;        
        int sum = 0;
        
        while (left <= n && right <= n) {
            if (sum < n) {
                right++;
                sum += right;
            } else if (sum == n) {
                sum -= left;
                left++;
                
                right++;                
                sum += right;
                
                answer++;
            } else {
                sum -= left;
                left++;
            }
        }
        
        
        return answer;
    }
}