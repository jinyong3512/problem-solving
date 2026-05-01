import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            datas.add(new Data(convertToMinutes(st.nextToken()), st.nextToken()));
        }
        
        Collections.sort(datas, new Comparator<Data>(){
            @Override
            public int compare(Data o1, Data o2) {
                return o1.time - o2.time;
            }
        });
        
        /////////////////////////////////////////////////////
        
        Map<String, Integer> sumTime = new HashMap<>();
        
        Map<String, Integer> memory = new HashMap<>();
        
        for (int i = 0; i < datas.size(); i++) {
            if (memory.containsKey(datas.get(i).number)) {
                
                int start = memory.get(datas.get(i).number);
                int end = datas.get(i).time;
                
                sumTime.put(
                    datas.get(i).number, 
                    sumTime.getOrDefault(datas.get(i).number, 0) + end - start);
                
                memory.remove(datas.get(i).number);
                
            } else {
                memory.put(datas.get(i).number, datas.get(i).time);
            }
        }
        
        for (String key: memory.keySet()) {
            String curNumber = key;
            int start = memory.get(curNumber);
            int end = convertToMinutes("23:59");

            sumTime.put(
                curNumber, 
                sumTime.getOrDefault(curNumber, 0) + end - start);
        }
        
        List<Data> answerList = new ArrayList<>();
        for (String key: sumTime.keySet()) {
            
            int fee = 0;
            
            if (sumTime.get(key) <= fees[0]) {
                fee = fees[1];
            } else {
                fee = fees[1] + (int)Math.ceil((sumTime.get(key) - fees[0]) / (double)fees[2]) * fees[3];
            }
            
            System.out.printf("%s %d %d\n", key, sumTime.get(key), fee);
            
            answerList.add(new Data(fee, key));
        }
        
        /////////////////////////////////////////////////////
        
        Collections.sort(answerList, new Comparator<Data>(){
            @Override
            public int compare(Data o1, Data o2) {
                return o1.number.compareTo(o2.number);
            }
        });
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i).time;
        }
        
        return answer;
    }
    
    private int convertToMinutes(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
    
    class Data {
        int time;
        String number;
        Data(int time, String number) {
            this.time = time;
            this.number = number;
        }
    }
}