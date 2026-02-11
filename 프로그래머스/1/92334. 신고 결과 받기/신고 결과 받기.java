import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        StringTokenizer st;
        
        boolean[][] visited = new boolean[id_list.length][id_list.length];
        
        HashMap<String, Integer> hashMap = new HashMap<>();        
        for (int i = 0; i < id_list.length; i++) {
            hashMap.put(id_list[i], i);
        }
        
        for (int i = 0; i < report.length; i++) {
            st = new StringTokenizer(report[i]);
            String first = st.nextToken();
            String second = st.nextToken();
            
            visited[hashMap.get(first)][hashMap.get(second)] = true;
        }
        
        for (int j = 0; j < id_list.length; j++) {
            int count = 0;
            for (int i = 0; i < id_list.length; i++) {
                if (visited[i][j])
                    count++;
            }
                                                  
            if (count >= k) {
                for (int i = 0; i < id_list.length; i++) {
                    if (visited[i][j])
                        answer[i]++;
                }
            }
        }
        
        return answer;
    }
}