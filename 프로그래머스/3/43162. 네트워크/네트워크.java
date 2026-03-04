import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {                          
        return calculateAnswer(n, computers);
    }
    
    private int calculateAnswer(int n, int[][] computers) {                
        int answer = 0;        
        boolean[] visited = new boolean[n];        
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            answer++;
            bfs(n, computers, visited, i);
        }
        
        return answer;
    }
    
    private void bfs(int n, int[][] computers, boolean[] visited, int index) {                
        visited[index] = true;
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(index);
        
        while (!queue.isEmpty()) {
            int curIndex = queue.remove();
            
            for (int i = 0; i < n; i++) {
                if (computers[curIndex][i] == 0) {
                    continue;
                }
                
                if (visited[i]) {
                    continue;
                }
                
                visited[i] = true;
                queue.add(i);
            }
            
        }
        
    }
    
}