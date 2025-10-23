class Solution {
    
    public static int answer = 0;
    public static boolean[] visited;
    
    public int solution(int n, int[][] q, int[] ans) {        
        visited = new boolean[n + 1];
        dfs(n, q, ans, 1, 0);
        return answer;
    }
    
    public void dfs(int n, int[][] q, int[] ans, int curN, int depth) {
        if (depth == 5) {
            int[] newAns = new int[ans.length];
            for (int i = 0; i < newAns.length; i++)
                newAns[i] = ans[i];
                
            if (can(n, q, newAns))
                answer++;
            
            return;
        }
        
        if (curN > n)
            return;
        
        visited[curN] = true;
        dfs(n, q, ans, curN + 1, depth + 1);
        visited[curN] = false;
        
        dfs(n, q, ans, curN + 1, depth);
        
    }
    
    public boolean can(int n, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {            
            for (int j = 0; j < q[0].length; j++) {
                if (visited[q[i][j]]) {
                   ans[i]--; 
                }
            }
        }
        
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] != 0)
                return false;
        }
        return true;
            
    }
}