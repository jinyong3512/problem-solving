import java.util.*;

class Truck {
    int weight;
    int startTime;
    Truck (int weight, int startTime) {
        this.weight = weight;
        this.startTime = startTime;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Truck> queue = new LinkedList<>();
        
        int time = 0;
        int curWeight = 0;
        for (int i = 0; i < truck_weights.length; i++) {
            time++;
            
            // 트럭 내리기
            if (!queue.isEmpty()) {
                if (queue.peek().startTime + bridge_length <= time) {
                    curWeight -= queue.remove().weight;
                }
            }
            
            // 트럭 올리기
            if (bridge_length > queue.size() && curWeight + truck_weights[i] <= weight) {
                queue.add(new Truck(truck_weights[i], time));
                curWeight += truck_weights[i];
            } else {
                i--;
            }
        }
        
        
        return time + bridge_length;
    }
}