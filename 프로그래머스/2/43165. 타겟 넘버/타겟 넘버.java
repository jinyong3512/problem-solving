class Solution {
    
    private static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        dfs(numbers, target, 0, 0);
            
        return answer;
    }
    
    private void dfs(int[] numbers, int target, int curIndex, int curSum) {
        if (curIndex == numbers.length) {
            if (curSum == target)
                answer++;
            return;
        }
        
        dfs(numbers, target, curIndex + 1, curSum + numbers[curIndex]);
        dfs(numbers, target, curIndex + 1, curSum - numbers[curIndex]);            
    }
}