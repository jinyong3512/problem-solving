import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {        
        // 1. lost와 reserve에 중복되는 것 제거하기. 그럼 진짜 잃어버린애와 진짜 빌려줄 수 있는 애가 나옴
        // 2. 정렬하고 reserve 돌면서 +-1 해서 무조건 lost Index 처리하기
        
        int[] students = new int[n + 1];
        // 0 자기꺼만 있는 애
        // -1 빌려야 하는 애
        // 1 빌려줄 수 있는 애 
        for (int i = 0; i < lost.length; i++) {
            students[lost[i]]--;
        }
        
        for (int i = 0; i < reserve.length; i++) {
            students[reserve[i]]++;
        }
        
        for (int i = 1; i <= n; i++) {
            if (students[i] == 1) {
                if (i - 1 >= 1 && students[i - 1] == -1) {
                    students[i - 1] = 0;
                    students[i] = 0;
                } else if (i + 1 <= n && students[i + 1] == -1) {
                    students[i + 1] = 0;
                    students[i] = 0;
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <=n ; i++) {
            if (students[i] == 0 || students[i] == 1)
                answer++;
        }
        
        return answer;
    }
}