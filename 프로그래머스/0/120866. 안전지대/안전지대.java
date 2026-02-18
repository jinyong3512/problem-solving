class Solution {
    
    public static int[] dy = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    public static int[] dx = new int[]{0, 0, -1, 1, -1, +1 , -1, +1};
    
    public int solution(int[][] board) {
        int answer = 0;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    visited[i][j] = true;
                    
                    for (int direction = 0; direction < 8; direction++) {
                        int newY = i + dy[direction];
                        int newX = j + dx[direction];
                        
                        if (newY < 0 || newY >= board.length || newX < 0 || newX >= board[0].length)
                            continue;
                        
                        visited[newY][newX] = true;
                    }
                }
            }
        }
        
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (!visited[i][j])
                    answer++;
            }
        }
        
        return answer;
    }
}