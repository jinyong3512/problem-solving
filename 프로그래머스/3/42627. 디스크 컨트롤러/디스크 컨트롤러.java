import java.util.*;

class Data {
    int takeTime;
    int requireTime;
    int jobIndex;
    Data (int takeTime, int requireTime, int jobIndex) {
        this.takeTime = takeTime;
        this.requireTime = requireTime;
        this.jobIndex = jobIndex;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0])
                    return 1;
                else if (o1[0] == o2[0]) {
                    if (o1[1] > o2[1])
                        return 1;
                    else if (o1[1] == o2[1])
                        return 0;
                    else
                        return -1;
                        
                } else
                    return -1;
                    
            }
        });
        
        PriorityQueue<Data> pQ = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.takeTime > o2.takeTime)
                    return 1;
                else if (o1.takeTime == o2.takeTime) {
                    if (o1.requireTime > o2.requireTime)
                        return 1;
                    else if (o1.requireTime == o2.requireTime) {
                        if (o1.jobIndex > o2.jobIndex) 
                            return 1;
                        else if (o1.jobIndex == o2.jobIndex)
                            return 0;
                        else
                            return -1;
                    } else 
                        return -1;
                } else
                    return -1;
            }
        });
        
        int curTime = 0;
        for (int i = 0; i < jobs.length; i++) {
            if (curTime >= jobs[i][0]) {
                pQ.add(new Data(jobs[i][1], jobs[i][0], i));
            } else {
                if (pQ.isEmpty()) {
                    curTime = jobs[i][0];
                    i--;
                } else {
                    Data curData = pQ.remove();
                    curTime += curData.takeTime;
                    answer += curTime - curData.requireTime;
                    System.out.printf("1 %d %d %d\n", curTime, curData.takeTime, curData.jobIndex);
                    i--;
                }
            }
        }
        
        while (!pQ.isEmpty()) {
            Data curData = pQ.remove();
            System.out.printf("2 %d %d %d\n", curTime, curData.takeTime, curData.jobIndex);
            curTime += curData.takeTime;                        
            answer += curTime - curData.requireTime;
        }
        
        
        return answer / jobs.length;
    }
}