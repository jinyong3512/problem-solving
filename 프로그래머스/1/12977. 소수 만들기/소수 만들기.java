class Solution {
    
    public static int answer = 0;
    
    public int solution(int[] nums) {
        // nC3
        combination(nums, 0, 0, 0);
    
        return answer;
    }
    
    public static void combination(int[] nums, int index, int depth, int sum) {
        if (depth == 3) {
            if (isPrimeNumber(sum))
                answer++;
            return;
        }
        
        if (index == nums.length)
            return;
        
        combination(nums, index + 1, depth + 1, sum + nums[index]);
        combination(nums, index + 1, depth, sum);
    }
    
    public static boolean isPrimeNumber(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}