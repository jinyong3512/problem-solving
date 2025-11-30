import java.util.*;

class Point {
    int y, x, depth;
    Point(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

class Solution {
    
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};
    
    public int solution(String[] maps) {
        
        Point pointS = null;
        Point pointE = null;
        Point pointL = null;
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                   pointS = new Point(i, j, 0); 
                } else if (maps[i].charAt(j) == 'E') {
                   pointE = new Point(i, j, 0); 
                } else if (maps[i].charAt(j) == 'L') {
                   pointL = new Point(i, j, 0); 
                }
            }
        }
        
        int answer1 = bfs(maps, pointS, pointL);
        if (answer1 == -1)
            return -1;
            
        int answer2 = bfs(maps, pointL, pointE);
        if (answer2 == -1)
            return -1;
        
        return answer1 + answer2;
    }
    
    public int bfs(String[] maps, Point startPoint, Point endPoint) {
        
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        visited[startPoint.y][startPoint.x] = true;
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        
        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();
            
            if (curPoint.y == endPoint.y && curPoint.x == endPoint.x) {
                return curPoint.depth;
            }
            
            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.y + dy[direction];
                int newX = curPoint.x + dx[direction];
                
                if (newY < 0 || newY >= maps.length || newX < 0 || newX >= maps[0].length())
                    continue;
                
                if (visited[newY][newX])
                    continue;
                
                if (maps[newY].charAt(newX) == 'X')
                    continue;
                
                visited[newY][newX] = true;
                queue.add(new Point(newY, newX, curPoint.depth + 1));
                
            }
            
        }
        
        return -1;
        
        
    }
}