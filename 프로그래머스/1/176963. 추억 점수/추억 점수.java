import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int j = 0; j < name.length; j++)
            hashMap.put(name[j], yearning[j]);
                
        for (int i = 0; i < photo.length; i++) {
            for (int j = 0; j < photo[i].length; j++) {
                answer[i] += hashMap.getOrDefault(photo[i][j], 0);
            }
        }
        
        return answer;
    }
}