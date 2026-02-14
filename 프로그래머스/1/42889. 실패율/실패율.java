import java.util.*;

class Data {
    int index;
    double probability;
    
    Data (int index, double probability) {
        this.index = index;
        this.probability = probability;
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N]; // 1 ~ N 단계까지 
        
        int[] clears = new int[N + 2]; // 1 ~ N + 1
        for (int i = 0; i < stages.length; i++) {
            clears[stages[i]]++;
        }
        
        int tmp = stages.length; // 총 사람 수 
        Data[] datas = new Data[N];
        for (int i = 1; i <= N; i++) {
            if (tmp - clears[i] == 0)
                datas[i - 1] = new Data(i, (double)clears[i]);
            else
                datas[i - 1] = new Data(i, (double)clears[i] / (tmp - clears[i]));
            tmp -= clears[i];
        }
        
        for (int i = 0; i < datas.length; i++) {
            System.out.printf("%d %f\n", datas[i].index, datas[i].probability);
        }        
        
        Arrays.sort(datas, new Comparator<Data>(){
            @Override
            public int compare(Data a, Data b) {
                if (a.probability > b.probability) {
                    return -1;
                } else if (a.probability == b.probability) {
                    if (a.index > b.index) {
                        return 1;
                    } else if (a.index == b.index) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return 1;
                }
            }
        });
        
        for (int i = 0; i < datas.length; i++) {
            answer[i] = datas[i].index;
        } 
        
        
        
        return answer;
    }
}