class Solution {
    
    public static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        // nPn
        boolean[] visited = new boolean[dungeons.length];
        permutation(k, dungeons, visited, 0);
        
        return answer;
    }
    
    public static void permutation(int curK, int[][] dungeons, boolean[] visited, int depth) {
        answer = Math.max(answer, depth);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                if (dungeons[i][0] <= curK) {
                    visited[i] = true;
                    permutation(curK - dungeons[i][1], dungeons, visited, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}