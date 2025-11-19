import java.util.*;

class Point {
    int y, x, depth;
    Point (int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

class Solution {
    
    public int[] dy = new int[]{-1, 1, 0, 0};
    public int[] dx = new int[]{0, 0, -1, 1};
    
    public int solution(String[] board) {
   
        Point startPoint = null;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    startPoint = new Point(i, j, 0);
                }
            }
        }
        
        return bfs(board, startPoint);
    }
    
    public int bfs(String[] board, Point startPoint) {
        
        boolean[][] visited = new boolean[board.length][board[0].length()];
        visited[startPoint.y][startPoint.x] = true;
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        
        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();
            
            for (int direction = 0; direction < 4; direction++) {
                int curY = curPoint.y;
                int curX = curPoint.x;
                while (true) {
                    curY += dy[direction];
                    curX += dx[direction];
                    
                    if (curY < 0 || curY >= board.length || curX < 0 || curX >= board[0].length()) {
                        break;
                    }
                    
                    if (board[curY].charAt(curX) == 'D')
                        break;
                }
                
                curY -= dy[direction];
                curX -= dx[direction];
                
                if (visited[curY][curX])
                    continue;
                
                if (board[curY].charAt(curX) == 'G')
                    return curPoint.depth + 1;
                
                visited[curY][curX] = true;
                queue.add(new Point(curY, curX, curPoint.depth + 1));
            }
        }
        
        return -1;
    }
}