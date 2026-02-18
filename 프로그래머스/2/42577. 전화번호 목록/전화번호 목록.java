import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        
        HashMap<String, Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book[i].length(); j++) {
                String cur = phone_book[i].substring(0, j + 1);
                
                if (hashMap.containsKey(cur))
                    return false;
            }
            hashMap.put(phone_book[i], true);
        }
        
        return true;
    }
}