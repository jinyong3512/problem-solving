import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 야근 피로도 => 남은 일의 작업량을 제곱하여 더한 값
        
        // 1 => 1
        // 2 => 4
        // 3 => 9
        // 4 => 16
        // 5 => 25
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1,Integer o2){
                return o2-o1;
            };
        });
        
        for(int value: works)
            pQ.add(value);
        
        for(int i = 0 ; i < n ;i++){
            if(pQ.isEmpty())
                break;
            
            int cur = pQ.remove();
            if(cur==1)
                continue;
            else
                pQ.add(cur-1);
        }
        
        long answer = 0;
        while(!pQ.isEmpty()){
            int cur = pQ.remove();
            answer += Math.pow(cur,2);
        }
        return answer;
    }
}