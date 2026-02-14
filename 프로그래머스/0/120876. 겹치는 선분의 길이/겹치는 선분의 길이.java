import java.util.*;

class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        
        boolean[] visited = new boolean[200]; 
        // -100~-99  99~100
        // 0 199 
        
        int left = Math.max(lines[0][0], lines[1][0]);
        int right = Math.min(lines[0][1], lines[1][1]);
        
        int left2 = Math.max(lines[1][0], lines[2][0]);
        int right2 = Math.min(lines[1][1], lines[2][1]);
        
        int left3 = Math.max(lines[0][0], lines[2][0]);
        int right3 = Math.min(lines[0][1], lines[2][1]);
        
        System.out.printf("%d %d\n", left, right);
        System.out.printf("%d %d\n", left2, right2);
        System.out.printf("%d %d\n", left3, right3);
        
        for (int i = left; i < right; i++) {
            visited[i + 100] = true;
        }
        for (int i = left2; i < right2; i++) {
            visited[i + 100] = true;
        }
        for (int i = left3; i < right3; i++) {
            visited[i + 100] = true;
        }
        
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}