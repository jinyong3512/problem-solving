import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }
        
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            String curSkill = skill_trees[i];
            
            boolean can = true;
            int index = 0;
            for (int j = 0; j < curSkill.length(); j++) {
                char curChar = curSkill.charAt(j);
                
                if (map.containsKey(curChar)) {
                    if (map.get(curChar) == index) {
                        index++;
                    } else {
                        can = false;
                    }
                } else {
                    
                }
            }
            
            if (can) {
                answer++;
            }
        }
        
        return answer;
    }
}