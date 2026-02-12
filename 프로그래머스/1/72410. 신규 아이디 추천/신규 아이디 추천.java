import java.util.*;

class Solution {
    public String solution(String new_id) {
        
        
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < new_id.length(); i++)
            arrayList.add(new_id.charAt(i));
        
        // 1단계
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) >= 'A' && arrayList.get(i) <= 'Z')
                arrayList.set(i, (char)(arrayList.get(i) - 'A' + 'a'));
        }
        
        // 2단계
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) >= 'a' && arrayList.get(i) <= 'z')
                continue;
            if (arrayList.get(i) >= '0' && arrayList.get(i) <= '9')
                continue;
            if (arrayList.get(i) == '-')
                continue;
            if (arrayList.get(i) == '_')
                continue;
            if (arrayList.get(i) == '.')
                continue;
            
            arrayList.remove(i);
            i--;
        }
        
        // 3단계
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i) == '.' && arrayList.get(i-1) == '.') {
                arrayList.remove(i);
                i--;
            }
        }
        
        // 4단계
        if (arrayList.size() != 0 && arrayList.get(0) == '.')
            arrayList.remove(0);
        if (arrayList.size() != 0 && arrayList.get(arrayList.size() - 1) == '.')
            arrayList.remove(arrayList.size() - 1);
        
        // 5단계
        if (arrayList.size() == 0)
            arrayList.add('a');
        
        // 6단계        
        while (arrayList.size() > 15) {
            arrayList.remove(arrayList.size() - 1);
        }
        if (arrayList.size() != 0 && arrayList.get(arrayList.size() - 1) == '.')
            arrayList.remove(arrayList.size() - 1);
        
        // 7단계
        if (arrayList.size() <= 2) {
            while (arrayList.size() <= 2)
                arrayList.add(arrayList.get(arrayList.size() - 1));
        }
        
        String answer = "";
        for (int i = 0; i < arrayList.size(); i++)
            answer += arrayList.get(i);
        return answer;
    }
}