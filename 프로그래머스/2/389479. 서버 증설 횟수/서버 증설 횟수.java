import java.util.*;

class Server {
    int endTime, count;
    Server (int endTime, int count) {
        this.endTime = endTime;
        this.count = count;
    }
}

class Solution {
    public int solution(int[] players, int m, int k) {
        
        int n = 1;
        int x = 0;
        
        Queue<Server> queue = new LinkedList<>();                        
        
        for (int i = 0; i < players.length; i++) {
            
            // 증설했던 것 빼주기
            if (!queue.isEmpty() && queue.peek().endTime == i) {                
                n -= queue.remove().count;
            }
            
            // 증설하기
            if (players[i] >= n * m) {
                int count = players[i] / m + 1 - n;
                
                queue.add(new Server(i + k, count));
                n += count;
                x += count;
            }
        }
        
        
        return x;
    }
}