import java.util.*;

class Solution {
    public int solution(String dirs) {
        
        List<Path> paths = new ArrayList<>();
        
        int curY = 0;
        int curX = 0;
                
        int answer = 0;
        
        for (int i = 0; i < dirs.length(); i++) {
            int newY = curY;
            int newX = curX;
            
            if (dirs.charAt(i) == 'U') {
                newY--;
            } else if (dirs.charAt(i) == 'D') {
                newY++;
            } else if (dirs.charAt(i) == 'R') {
                newX++;
            } else {
                newX--;
            }
            
            if (newY < -5 || newY > 5 || newX < -5 || newX > 5) {
                continue;
            }
            
            boolean visited = false;
            for (Path path: paths) {
                if (curY == path.curY && curX == path.curX && newY == path.newY && newX == path.newX) {
                    visited = true;
                } else if (curY == path.newY && curX == path.newX && newY == path.curY && newX == path.curX) {
                    visited = true;
                }
            }
            
            if (!visited) {
                answer++;
                paths.add(new Path(curY, curX, newY, newX));
            }
            
            curY = newY;
            curX = newX;
        }
        
        return answer;
    }
    
    private static class Path {
        int curY, curX;
        int newY, newX;
        
        Path (int curY, int curX, int newY, int newX) {
            this.curY = curY;
            this.curX = curX;
            this.newY = newY;
            this.newX = newX;
        }
    }
}