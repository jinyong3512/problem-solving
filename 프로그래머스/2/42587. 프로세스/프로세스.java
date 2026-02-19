import java.util.*;

class Data {
    int priority;
    int index;
    Data (int priority, int index) {
        this.priority = priority;
        this.index = index;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        
        Queue<Data> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Data(priorities[i], i));
        }
        
        //
        
        int answer = 0;
        while (!queue.isEmpty()) {
            Data curData = queue.remove();
            
            boolean canRemove = true;
            for (Data tmp: queue) {
                if (tmp.priority > curData.priority)
                    canRemove = false;
            }
            
            if (canRemove) {
                answer++;
                if (curData.index == location)
                    break;
            } else {
                queue.add(curData);
            }
        }
        
        return answer;
    }
}