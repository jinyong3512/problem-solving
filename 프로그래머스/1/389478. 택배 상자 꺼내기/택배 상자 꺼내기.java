class Solution {
    
    public static int dy[] = {-1, 1, 0, 0};
    public static int dx[] = {0, 0, -1, 1};    
    
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        int[][] arr = new int[100][100];
        int num_y = -1;
        int num_x = -1;
        
        int y = 99;
        int x = 0;
        
        int direction = 3;
        
        for (int i = 1; i <= n; i++) {
            arr[y][x] = i;
            
            if (i == num) {
                num_y = y;
                num_x = x;
            }
            
            if (i % w == 0) {
                if (i / w % 2 == 1) {
                    y--;
                    direction = 2;
                } else {
                    y--;
                    direction = 3;
                }
            } else {
                y = y + dy[direction];
                x = x + dx[direction];
            }
        }
        
        for (int i = num_y; i >= 0; i--) {
            if (arr[i][num_x] == 0)
                break;            
            answer++;
        }
        
        return answer;
    }
}