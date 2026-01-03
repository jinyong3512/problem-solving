import java.util.*;

class Point {
    int y, x;
    Point (int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (visited[i][j])
                    continue;
                else if (maps[i].charAt(j) == 'X')
                    continue;
                else {                    
                    arrayList.add(bfs(maps, visited, i, j));
                }
            }
        }
        
        Collections.sort(arrayList);
        
        if (arrayList.size() == 0) {
            return new int[]{-1};
        } else {
            int[] answer = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++)
                answer[i] = arrayList.get(i);
            return answer;
        }
        
    }
    
    public int bfs(String[] maps, boolean[][] visited, int i, int j) {
        int count = maps[i].charAt(j) - '0';
        
        visited[i][j] = true;
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        
        while (!queue.isEmpty()) {
            
            Point curPoint = queue.remove();
            
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
                count += maps[newY].charAt(newX) - '0';
                queue.add(new Point(newY, newX));
            }
        }
        return count;
    }
}