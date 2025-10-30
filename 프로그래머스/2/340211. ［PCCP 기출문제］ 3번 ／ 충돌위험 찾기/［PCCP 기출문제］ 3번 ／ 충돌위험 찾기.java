import java.util.*;

class Point {
    int y, x;
    Point (int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Robot {
    int y, x, routeIndex;
    boolean out;
    Robot (int y, int x, int routeIndex, boolean out) {
        this.y = y;
        this.x = x;
        this.routeIndex = routeIndex;
        this.out = out;
    }
}

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        // 포인트 기억 // 1번 포인트는 1, 1
        HashMap<Integer, Point> hashMap = new HashMap<>();        
        for (int i = 0; i < points.length; i++) {
            hashMap.put(i + 1, new Point(points[i][0], points[i][1]));
        }
        
        // 로봇들 어디 // 1번 로봇은 1, 1
        Robot[] robots = new Robot[routes.length + 1];
        for (int i = 0; i < routes.length; i++) {            
            robots[i + 1] = new Robot(hashMap.get(routes[i][0]).y, hashMap.get(routes[i][0]).x, 1, false);
        }
        answer += collisionCount(robots);
         
        while (true) {
        //for (int time = 1; time <= 2; time++) {
            
            boolean breakFlag = true;
            
            // 로봇들 이동
            for (int i = 1; i < robots.length; i++) {
                int curY = robots[i].y;
                int curX = robots[i].x;
                int curRouteIndex = robots[i].routeIndex;
                
                //System.out.printf("%d %d %d %d\n", i, curY, curX, curRouteIndex);
                
                if (curRouteIndex == routes[i-1].length){
                    robots[i] = new Robot(curY, curX, curRouteIndex, true);
                    continue;
                }
                
                breakFlag = false;
                
                Point goalPoint = hashMap.get(routes[i - 1][curRouteIndex]);
                
                int newY = curY;
                int newX = curX;
                int newRouteIndex = curRouteIndex;
                
                if (curY > goalPoint.y)
                    newY--;
                else if (curY < goalPoint.y)
                    newY++;
                else {
                    if (curX > goalPoint.x)
                        newX--;
                    else if (curX < goalPoint.x)
                        newX++;
                    else
                        System.out.println("버그");
                }
                
                if (newY == goalPoint.y && newX == goalPoint.x)
                    newRouteIndex = curRouteIndex + 1;
                else
                    newRouteIndex = curRouteIndex;
                
                robots[i] = new Robot(newY, newX, newRouteIndex, false);
            }
            
            if (breakFlag)
                break;
            
            answer += collisionCount(robots);
            
        }
        return answer;
    }   
    
    public int collisionCount(Robot[] robots) {
        int answer = 0;
        
        boolean[] visited = new boolean[robots.length];
        
        for (int i = 1 ; i < robots.length; i++) {
            if (robots[i].out)
                continue;
            for (int j = i + 1; j < robots.length; j++) {
                if (robots[j].out)
                    continue;
                
                if (robots[i].y == robots[j].y && robots[i].x == robots[j].x) {
                    if (visited[i]) {                        
                        visited[j] = true;
                    } else {
                        visited[i] = true;
                        visited[j] = true;
                        answer++;
                    }
                }
                
            }
        }
        
        return answer;
    }
}