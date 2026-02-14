class Solution {
    // 하 좌 상 우
    // 3 3 3 2 2 1 1
    // 4 4 4 3 3 2 2 1 1
    public static int[] dy = {1, 0, -1, 0};
    public static int[] dx = {0, -1, 0, 1};
    
    public int[][] solution(int n) {
        // int[][] answer = {};        
        int[][] answer = new int[n][n];
                    
        // 초기 셋팅
        int number = 1;
        int y = 0;
        int x = 0;      
        answer[0][0] = number;
        
        int depth = n -1;
        int direction = 0;
        
        // 한번 우
        for (int i = 0; i < depth; i++) {
            x++;
            number++;
            answer[y][x] = number;
        }
        
        for (; depth >= 1; depth--) {        
            
            for (int i = 0; i < depth; i++) {
                y = y + dy[direction];
                x = x + dx[direction];
                number++;
                answer[y][x] = number;
            }
            direction++;
            
            for (int i = 0; i < depth; i++) {
                y = y + dy[direction];
                x = x + dx[direction];
                number++;
                answer[y][x] = number;
            }
            direction++;
            if (direction == 4)
                direction = 0;
            
        }
        
        return answer;
    }
}