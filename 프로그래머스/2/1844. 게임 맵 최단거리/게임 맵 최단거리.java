import java.util.*;

class Solution {    
    private static final int[] dy = new int[]{-1, 1, 0, 0};
    private static final int[] dx = new int[]{0, 0, -1, 1};
    
    public int solution(int[][] maps) {        
        return calculateAnswer(maps);
    }
    
    private int calculateAnswer(int[][] maps) {
        int answer = -1;
        
        boolean[][] visited = new boolean[maps.length][maps[0].length];                
        Queue<Point> queue = new ArrayDeque<>();
        
        visited[0][0] = true;
        queue.add(new Point(0, 0, 1));
        
        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();
            
            if (curPoint.getY() == maps.length - 1 && curPoint.getX() == maps[0].length - 1) {
                answer = curPoint.getDepth();
            }
            
            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.getY() + dy[direction];
                int newX = curPoint.getX() + dx[direction];
                
                if (newY < 0 || newY >= maps.length || newX < 0 || newX >= maps[0].length) {
                    continue;
                }
                if (visited[newY][newX]) {
                    continue;
                }
                if (maps[newY][newX] == 0) {
                    continue;
                }
                
                visited[newY][newX] = true;
                queue.add(new Point(newY, newX, curPoint.getDepth() + 1));                
            }
        }
        
        return answer;
    }
    
    private static class Point {
        private final int y;
        private final int x;
        private final int depth;
        
        Point(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
        
        int getY() {
            return y;
        }
        int getX() {
            return x;
        }
        int getDepth() {
            return depth;
        }
    }
}