import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < participant.length; i++) {
            hashMap.put(participant[i], hashMap.getOrDefault(participant[i], 0) + 1);
        }
        
        for (int i = 0; i < completion.length; i++) {
            if (hashMap.get(completion[i]) == 1) {
                hashMap.remove(completion[i]);
            } else {
                hashMap.put(completion[i], hashMap.get(completion[i]) - 1);
            }
        }
        
        for (String key: hashMap.keySet())
            answer = key;
                    
        return answer;
    }
}