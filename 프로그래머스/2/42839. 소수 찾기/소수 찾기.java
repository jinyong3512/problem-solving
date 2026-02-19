import java.util.*;

class Solution {
    
    public static HashMap<Integer, Boolean> hashMap = new HashMap<>();
    
    public int solution(String numbers) {
        
        // nP1 ~ nPn
        for (int i = 1; i <= numbers.length(); i++) {
            boolean[] visited = new boolean[numbers.length()];
            permutation(numbers, visited, i, "");
        }
        
        for (Integer key: hashMap.keySet())
            System.out.println(key);
        
        int answer = 0;
        for (Integer key: hashMap.keySet()) {
            if (isPrimeNumber(key))
                answer++;
        }
        
        return answer;
    }
    
    public static void permutation(String numbers, boolean[] visited, int r, String cur) {
        if (r == cur.length()) {
            hashMap.put(Integer.parseInt(cur), true);
            return;
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(numbers, visited, r, cur + numbers.charAt(i));
                visited[i] = false;
            }
        }
    }
    
    public boolean isPrimeNumber(int number) {
        if (number < 2)
            return false;
        
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
}