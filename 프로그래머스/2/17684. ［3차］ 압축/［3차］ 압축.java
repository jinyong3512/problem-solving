import java.util.*;

class Solution {
    public int[] solution(String msg) {
        
        Map<String, Integer> map = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            map.put(String.valueOf(i), map.size() + 1);
        }
        
        // for (String key: map.keySet()) {
        //     System.out.printf("%s %d\n", key, map.get(key));
        // }
        
        List<Integer> answerList = new ArrayList<>();
        
        int left = 0;
        int right = 1;
        String tmp = "";
        while (true) {
            tmp = msg.substring(left, right);
            
            if (map.containsKey(tmp)) {
                right++;
                if (right > msg.length()) {
                    answerList.add(map.get(tmp));
                    break;
                }
            } else {
                answerList.add(map.get(tmp.substring(0, tmp.length() - 1)));
                map.put(tmp, map.size() + 1);
                left = right - 1;
            }
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}