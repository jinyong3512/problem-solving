import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        Map<String, Integer> str1Map = makeStrMap(str1);
        Map<String, Integer> str2Map = makeStrMap(str2);
        
        // 교집합
        int son = 0;        
        for (String key: str1Map.keySet()) {
            int count1 = str1Map.getOrDefault(key, 0);
            int count2 = str2Map.getOrDefault(key, 0);
            
            son += Math.min(count1, count2);
        }
        
        // 합집합
        int mother = 0;
        for (String key: str1Map.keySet()) {
            int count1 = str1Map.getOrDefault(key, 0);
            int count2 = str2Map.getOrDefault(key, 0);
            
            mother += Math.max(count1, count2);
        }
        for (String key: str2Map.keySet()) {
            int count1 = str1Map.getOrDefault(key, 0);
            int count2 = str2Map.getOrDefault(key, 0);
            
            if (count1 == 0) {
                mother += count2;
            }
        }       
        
        //System.out.println(son);
        //System.out.println(mother);
            
        if (mother == 0) {
            son = 1;
            mother = 1;
        }
        
        return (int)((double)son / mother * 65536);
    }
    
    private static Map<String, Integer> makeStrMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 1; i < str.length(); i++) {
            boolean can = true;
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                
            } else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                
            } else {
                can = false;
            }
            
            if (str.charAt(i - 1) >= 'a' && str.charAt(i - 1) <= 'z') {
                
            } else if (str.charAt(i - 1) >= 'A' && str.charAt(i - 1) <= 'Z') {
                
            } else {
                can = false;
            }            
            
            if (can) {                
                String cur = str.substring(i - 1, i + 1).toLowerCase();                
                map.put(cur, map.getOrDefault(cur, 0) + 1);                
            }
        }        
        
        return map;
    }
}