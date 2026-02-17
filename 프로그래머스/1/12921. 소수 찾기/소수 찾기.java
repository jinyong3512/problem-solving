class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = 2; i <= n; i++) {
            if (isPalindrom(i))
                answer++;
        }
        
        return answer;
    }
    
    public static boolean isPalindrom(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}