import java.util.*;
import java.io.*;

class Point {
    int y, x;
    
    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        
        ArrayList<Integer> answer = new ArrayList<>();
        function1(maps, answer);
        
        Collections.sort(answer);
        
        if (answer.size() == 0) {
            return new int[]{-1};
        } else {        
            int[] answer2 = new int[answer.size()];
            for (int i = 0; i < answer.size(); i++) {
                answer2[i] = answer.get(i);
            }

            return answer2;
        }
    }
    
    public void function1(String[] maps, ArrayList<Integer> answer) {
        
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                
                if (visited[i][j])
                    continue;
                else if (maps[i].charAt(j) == 'X')
                    continue;
                else {
                    visited[i][j] = true;
                    
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i, j));
                    
                    int count = maps[i].charAt(j) - '0';
                                        
                    while(!queue.isEmpty()) {
                        Point curPoint = queue.remove();

                        for(int direction = 0; direction < 4; direction++) {
                            int newY = curPoint.y + dy[direction];
                            int newX = curPoint.x + dx[direction];

                            if(newY < 0 || newY >= maps.length || newX < 0 || newX >= maps[0].length())
                                continue;
                            
                            if(visited[newY][newX])
                                continue;
                            
                            if(maps[newY].charAt(newX) == 'X')
                                continue;
                            
                            visited[newY][newX] = true;
                            queue.add(new Point(newY, newX));
                            count += maps[newY].charAt(newX) - '0';
                        }
                    }
                    
                    answer.add(count);
                    
                }
            }
        }
    }
}