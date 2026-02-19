import java.util.*;

class Data {
    int progress;
    int speed;
    
    Data (int progress, int speed) {
        this.progress = progress;
        this.speed = speed;
    }
}

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Stack<Data> stack = new Stack<>();
        for (int i = progresses.length - 1; i >= 0; i--)
            stack.push(new Data(progresses[i], speeds[i]));
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.isEmpty()) {
            Data curData = stack.pop();
            
            int q = (100 - curData.progress) / curData.speed;
            int r = (100 - curData.progress) % curData.speed;
            if (r != 0)
                q++;
            
            int count = 1;
            while (!stack.isEmpty()) {
                Data nextData = stack.peek();
                int q2 = (100 - nextData.progress) / nextData.speed;
                int r2 = (100 - nextData.progress) % nextData.speed;
                if (r2 != 0)
                    q2++;
                
                if (q >= q2) {
                    stack.pop();
                    count++;
                } else {
                    break;
                }
            }
            arrayList.add(count);
        }
        
        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            answer[i] = arrayList.get(i);
        
        return answer;
    }
}