import java.util.*;
import java.io.*;

public class Data{
    int start;
    int end;
    Data(int start,int end){
        this.start= start;
        this.end =end;
    }
}

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 그리디 문제네 탐욕적으로 제일 빨리 시작 한 것의 끝부분을 point 하자
        // 어떻게 정렬할래? 제일 빨리 끝나는 것을 기준으로 정렬?
        
        Data[] datas = new Data[targets.length];
        
        for(int i =0 ; i < targets.length;i++)
            datas[i] = new Data(targets[i][0],targets[i][1]);
        
        Arrays.sort(datas,new Comparator<Data>(){
            @Override
            public int compare(Data o1,Data o2){
                if(o1.end > o2.end)
                    return 1;
                else if (o1.end == o2.end)
                    return 0;
                else
                    return -1;
            }
            
        });
        
        double shoot_time = datas[0].end-0.5;
        answer++;
        for(int i =0 ; i < datas.length; i++){
            if(datas[i].start < shoot_time && shoot_time < datas[i].end)
                continue;
            else{
                shoot_time = datas[i].end-0.5;
                answer++;
            }
        }
        
        
        return answer;
    }
}