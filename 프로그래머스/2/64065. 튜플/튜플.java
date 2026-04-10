import java.util.*;

class Solution {
    public int[] solution(String s) {
    
        List<List<Integer>> bigList = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(s, "{");                
        while (st.hasMoreTokens()) {            
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), ",");
            
            List<Integer> smallList = new ArrayList<>();
            
            while(st2.hasMoreTokens()) {
                String cur = st2.nextToken();
                
                if (cur.length() - 2 >= 0 && cur.charAt(cur.length() - 2) == '}') {
                    cur = cur.substring(0, cur.length() - 2);
                } else if (cur.length() - 1 >= 0 && cur.charAt(cur.length() - 1) == '}') {
                    cur = cur.substring(0, cur.length() - 1);
                }
                
                smallList.add(Integer.parseInt(cur));
            }
            
            bigList.add(smallList);
        }
        
        Collections.sort(bigList, new Comparator<List>(){
            @Override
            public int compare(List o1, List o2) {
                return o1.size() - o2.size();
            }
        });
        
        List<Integer> answerList = new ArrayList<>();
        Map<Integer, Integer> answerMap = new HashMap<>();
        
        for (List<Integer> smallList: bigList) {
            
            Map<Integer, Integer> tmpMap = new HashMap<>();
            for (int value: smallList) {
                tmpMap.put(value, tmpMap.getOrDefault(value, 0) + 1);
            }
            
            for (int tmpKey: tmpMap.keySet()) {
                
                int tmpCount = tmpMap.get(tmpKey);
                int answerCount = answerMap.getOrDefault(tmpKey, 0);
                
                if (answerCount == 0 || answerCount + 1 == tmpCount) {
                    answerList.add(tmpKey);
                    answerMap.put(tmpKey, answerMap.getOrDefault(tmpKey, 0) + 1);
                }
            }
            
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}