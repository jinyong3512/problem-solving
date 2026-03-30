import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        int[] arr = new int[10000000 + 1];
        for (int i = 0; i < tangerine.length; i++) {
            arr[tangerine[i]]++;
        }
        
        Arrays.sort(arr);
              
        int answer = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            answer++;
            sum += arr[arr.length - 1- i];
            
            if (sum >= k) {
                break;
            }
        }
        
        return answer;
    }
}