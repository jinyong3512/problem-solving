import java.util.*;

class Solution {
    public int[] solution(String s) {
        ArrayList<Integer> arrayList = new ArrayList<>();        
        HashMap<Character, Integer> hashMap = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            
            if (hashMap.containsKey(cur)) {
                arrayList.add(i - hashMap.get(cur));                
            } else {
                arrayList.add(-1);                
            }
            
            hashMap.put(cur, i);
        }
        
        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            answer[i] = arrayList.get(i);
        }
        
        return answer;
    }
}