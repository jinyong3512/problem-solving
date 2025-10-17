import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String,Integer> hashMap = new HashMap<>();        
        int[][] map = new int[friends.length][friends.length];
        int[] points = new int[friends.length];
                
        for (int i = 0; i < friends.length; i++)
            hashMap.put(friends[i], i);
        
        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            String A = st.nextToken();
            String B = st.nextToken();
            
            map[hashMap.get(A)][hashMap.get(B)]++;
            map[hashMap.get(B)][hashMap.get(A)]--;
            
            points[hashMap.get(A)]++;
            points[hashMap.get(B)]--;
        }
        
        int answer = 0;
        
        for (int i = 0; i < map.length; i++) {
            int tmpAnswer = 0;
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > map[j][i]) {
                    tmpAnswer++;
                } else if (map[i][j] == map[j][i] && points[i] > points[j]) {
                    tmpAnswer++;
                }
            }
            
            answer = Math.max(answer, tmpAnswer);
        }
        
        return answer;
    }
}