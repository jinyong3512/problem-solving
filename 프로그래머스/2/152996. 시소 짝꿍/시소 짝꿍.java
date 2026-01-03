import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        
        for (int i = 0; i < weights.length; i++) {   
            // System.out.printf("i = %d\n", i);
            // System.out.println(upperBound(weights, i, 2, 2) - lowerBound(weights, i, 2, 2));
            // System.out.println(upperBound(weights, i, 3, 2) - lowerBound(weights, i, 3, 2));
            // System.out.println(upperBound(weights, i, 4, 2) - lowerBound(weights, i, 4, 2));
            // System.out.println(upperBound(weights, i, 4, 3) - lowerBound(weights, i, 4, 3));
            
            answer += upperBound(weights, i, 2, 2) - lowerBound(weights, i, 2, 2);
            answer += upperBound(weights, i, 3, 2) - lowerBound(weights, i, 3, 2);
            answer += upperBound(weights, i, 4, 2) - lowerBound(weights, i, 4, 2);
            answer += upperBound(weights, i, 4, 3) - lowerBound(weights, i, 4, 3);            
        }
                
        return answer;
    }
    
    public int lowerBound(int[] weights, int i, int a, int b) {
        int left = i + 1;
        int right = weights.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (weights[mid] * b < weights[i] * a)
                left = mid + 1;
            else
                right = mid;
        }
        
        return left;
    }
    
    public int upperBound(int[] weights, int i, int a, int b) {
        int left = i + 1;
        int right = weights.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (weights[mid] * b <= weights[i] * a)
                left = mid + 1;
            else
                right = mid;
        }
        
        return left;        
    }
            
}