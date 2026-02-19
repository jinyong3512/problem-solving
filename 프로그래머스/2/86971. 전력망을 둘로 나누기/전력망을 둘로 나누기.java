class Solution {
    
    public static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
                
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }
        
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 1) {
                    
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                    
                    boolean[] visited = new boolean[graph.length];
                    visited[1] = true;
                    dfs(graph, visited, 1);
                    int count = 0;
                    for (int k = 0; k < visited.length; k++) {
                        if (visited[k])
                            count++;
                    }
                    answer = Math.min(answer, Math.abs(n - count - count));
                    
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }
        
        return answer;
    }
    
    public static void dfs(int[][] graph, boolean[] visited, int curVertex) {
        for (int i = 1; i < graph.length; i++) {
            if (visited[i])
                continue;
            
            if (graph[curVertex][i] == 1) {
                visited[i] = true;
                dfs(graph, visited, i);
            }
        }
    }
    
}