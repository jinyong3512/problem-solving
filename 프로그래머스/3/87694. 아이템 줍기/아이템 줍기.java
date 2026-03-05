import java.util.*;

class Solution {
    
    private static final int[] DY = new int[]{-1, 1, 0, 0};
    private static final int[] DX = new int[]{0, 0, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = createMap(rectangle);
        // printMap(map);
        
        int answer = bfs(rectangle, characterX, characterY, itemX, itemY, map);
        return answer;
    }
    
    private int[][] createMap(int[][] rectangle) {
        int[][] map = new int[51 * 2][51 * 2];
        
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            for (int y = y1; y <= y2; y++) {
                map[y][x1] = 1;
                map[y][x2] = 1;
            }
            
            for (int x = x1; x <= x2; x++) {
                map[y1][x] = 1;
                map[y2][x] = 1;
            }
        }
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {                
                if (isInside(rectangle, i, j)) {
                    map[i][j] = 0;
                }
            }
        }
        
        return map;
    }
    
    private void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%d", map[map.length - 1 - i][j]);
            }
            System.out.println();
        }
    }
    
    private int bfs(int[][] rectangle, int characterX, int characterY, int itemX, int itemY, int[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[characterY * 2][characterX * 2] = true;
        
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(characterY * 2, characterX * 2, 0));
        
        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();
            
            // System.out.printf("%d %d %d\n", curPoint.getY(), curPoint.getX(), curPoint.getDepth());
            
            if (curPoint.getY() == itemY * 2 && curPoint.getX() == itemX * 2) {
                return curPoint.getDepth() / 2;
            }
            
            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.getY() + DY[direction];
                int newX = curPoint.getX() + DX[direction];
                
                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length) {
                    continue;
                }
                if (map[newY][newX] == 0) {
                    continue;
                }
                if (visited[newY][newX]) {
                    continue;
                }
                
                visited[newY][newX] = true;
                queue.add(new Point(newY, newX, curPoint.getDepth() + 1));
            }
        }
        
        return -1;
    }
    
    private boolean isInside(int[][] rectangle, int newY, int newX) {
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            if (newY > y1 && newY < y2 && newX > x1 && newX < x2) {
                return true;
            }
        }
        
        return false;
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