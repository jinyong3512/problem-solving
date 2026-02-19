import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int q = (100 - progresses[i]) / speeds[i];
            int r = (100 - progresses[i]) % speeds[i];
            if (r != 0)
                q++;
            
            int count = 1;
            for(i++; i < progresses.length; i++) {
                int q2 = (100 - progresses[i]) / speeds[i];
                int r2 = (100 - progresses[i]) % speeds[i];
                if (r2 != 0)
                    q2++;
                
                if (q >= q2) {
                    count++;
                } else {
                    i--;
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