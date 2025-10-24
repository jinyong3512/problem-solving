import java.util.*;

class Point {
    int y, x;
    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Solution {
    
    public int dy[] = new int[]{-1, 1, 0, 0};
    public int dx[] = new int[]{0, 0, -1, 1};
    
    public int solution(String[] storage, String[] requests) {        
        char[][] arr = new char[storage.length][storage[0].length()];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                arr[i][j] = storage[i].charAt(j);
        }
                        
        for (String request: requests) {
            if (request.length() == 1){
                arr = function1(arr, request.charAt(0));
            } else {
                function2(arr, request.charAt(0));
            }
        }
        
        int answer = 0;
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 'a')
                    answer++;
            }
        }
        
        return answer;
    }
    public char[][] function1(char[][] arr, char cur) {
        char[][] newArr = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        Queue<Point> queue = new LinkedList<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == cur) {
                newArr[i][0] = 'a';
                visited[i][0] = true;
            }
            if (arr[i][0] == 'a') {
                visited[i][0] = true;
                queue.add(new Point(i, 0));
            }
            if (arr[i][arr[0].length - 1] == cur) {
                newArr[i][arr[0].length - 1] = 'a';
                visited[i][arr[0].length - 1] = true;
            }
            if (arr[i][arr[0].length - 1] == 'a') {
                visited[i][arr[0].length - 1] = true;
                queue.add(new Point(i, arr[0].length - 1));
            }
        }
        
        for (int j = 1; j < arr[0].length - 1; j++) {
            if (arr[0][j] == cur) {
                newArr[0][j] = 'a';
                visited[0][j] = true;
            }
            if (arr[0][j] == 'a') {                
                visited[0][j] = true;
                queue.add(new Point(0, j));
            }
            if (arr[arr.length-1][j] == cur) {
                newArr[arr.length-1][j] = 'a';
                visited[arr.length-1][j] = true;
            }
            if (arr[arr.length-1][j] == 'a') {                
                visited[arr.length-1][j] = true;
                queue.add(new Point(arr.length-1, j));
            }            
            
            while (!queue.isEmpty()) {
                Point curPoint = queue.remove();
                
                for (int d = 0; d < 4; d++) {
                    int newY = curPoint.y + dy[d];
                    int newX = curPoint.x + dx[d];
                    
                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        continue;
                    if (visited[newY][newX])
                        continue;
                    if (arr[newY][newX] == cur) {
                        visited[newY][newX] = true;
                        newArr[newY][newX] = 'a';
                    } 
                    
                    if (arr[newY][newX] == 'a') {
                        visited[newY][newX] = true;
                        queue.add(new Point(newY, newX));
                    }
                    
                }
            }
        }
        
        
        return newArr;
    }
    
    public void function2(char[][] arr, char cur) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == cur)
                    arr[i][j] = 'a';
            }
        }
    }
}