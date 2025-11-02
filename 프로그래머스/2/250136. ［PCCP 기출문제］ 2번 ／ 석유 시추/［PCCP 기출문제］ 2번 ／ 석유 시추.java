import java.util.*;

class Point {
    int y, x;
    Point (int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    public int[] dy = new int[]{-1, 1, 0, 0};
    public int[] dx = new int[]{0, 0, -1, 1};
    
    public int solution(int[][] land) {
        
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        
        int[][] landNumber = new int[land.length][land[0].length];
        int curLandNumber = 0;
        for (int i = 0; i < landNumber.length; i++) {
            for (int j = 0; j < landNumber[0].length; j++) {
                if (land[i][j] == 1 && landNumber[i][j] == 0) {
                    curLandNumber++;
                    int curCount = numberingLand(land, landNumber, curLandNumber, i, j);
                    hashMap.put(curLandNumber, curCount);
                }                
            }
        }
        /*
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                System.out.printf("%d ",landNumber[i][j]);
            }
            System.out.println();
        }
        
        for (int key: hashMap.keySet()) {
            System.out.printf("%d %d\n", key, hashMap.get(key));
        }
        */
        
        int answer = 0;
        
        for (int j = 0; j < land[0].length; j++) {
                     
            boolean[] visited = new boolean[curLandNumber + 1];            
            for (int i = 0; i < land.length; i++) {
                visited[landNumber[i][j]] = true;
            }   
            
            int tmpAnswer = 0;
            for (int k = 1; k < visited.length; k++) {
                if (visited[k])
                    tmpAnswer += hashMap.get(k);
            }
            
            answer = Math.max(answer,tmpAnswer);
        }
        
        return answer;
    }
    
    public int numberingLand(int[][] land, int[][] landNumber, int curLandNumber, int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
                
        landNumber[i][j] = curLandNumber;
        
        int count = 1;
        
        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();
            
            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.y + dy[direction];
                int newX = curPoint.x + dx[direction];
                
                if (newY < 0 || newY >= land.length || newX < 0 || newX >= land[0].length)
                    continue;
                
                if (landNumber[newY][newX] != 0)
                    continue;
                
                if (land[newY][newX] == 0)
                    continue;
                
                landNumber[newY][newX] = curLandNumber;
                queue.add(new Point(newY, newX));
                count++;
            }
        }
        
        return count;
    }
}