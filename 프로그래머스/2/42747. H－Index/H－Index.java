import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        // 0 1 3 5 6
        for (int h = 0; h <= 10000; h++) {
            int index = lowerBound(citations, h); // h 이상인 값의 index
            if (index == citations.length)
                continue;
            
            int lowCount, highCount;
            if (citations[index] == h) {
                lowCount = index + 1; // 3개
                highCount = citations.length - index; // 3개
            } else {
                lowCount = index; // 2개
                highCount = citations.length - index; // 3개
            }
            
            if (lowCount <= h && highCount >= h)
                answer = h;
        }
        
        return answer;
    }
    
    public int lowerBound(int[] citations, int value) {
        int left = 0;
        int right = citations.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (citations[mid] > value) {
                right = mid;
            } else if (citations[mid] == value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}