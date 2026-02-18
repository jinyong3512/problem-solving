class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (int i = 0; i < babbling.length; i++) {
            if (isCan(babbling[i]))
                answer++;
        }
        
        
        return answer;
    }
    
    public static boolean isCan(String babbling) {
        
        String lastWord = "";
        String curWord = "";
        
        for (int i = 0; i < babbling.length(); i++) {
            
            // a y e w o m
            
            if (babbling.charAt(i) == 'a') {
                
                if (curWord.isEmpty()) {
                    curWord += 'a';
                } else if (curWord.equals("ay") || curWord.equals("m")) {
                    curWord += 'a';
                    if (lastWord.equals(curWord))
                        return false;
                    else {
                        lastWord = curWord;
                        curWord = "";
                    }
                } else {
                    return false;
                }
                
            } else if (babbling.charAt(i) == 'y') {
                
                if (curWord.isEmpty()) {
                    curWord += 'y';
                } else if (curWord.equals("a")) {
                    curWord += 'y';
                } else {
                    return false;
                }
                
            } else if (babbling.charAt(i) == 'w') {
                
                if (curWord.isEmpty()) {
                    curWord += 'w';
                } else {
                    return false;
                }
                
            } else if (babbling.charAt(i) == 'm') {
                if (curWord.isEmpty()) {
                    curWord += 'm';
                } else {
                    return false;
                }
            } else if (babbling.charAt(i) == 'e') {
                if (curWord.equals("y")) {
                    curWord += 'e';
                    if (lastWord.equals(curWord))
                        return false;
                    else {
                        lastWord = curWord;
                        curWord = "";
                    }
                } else {
                    return false;
                }
            } else if (babbling.charAt(i) == 'o') {
                if (curWord.equals("w")) {
                    curWord += 'o';
                } else if (curWord.equals("wo")) {
                    curWord += 'o';
                    if (lastWord.equals(curWord)) {
                        return false;
                    } else {
                        lastWord = curWord;
                        curWord = "";
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        if (curWord.isEmpty())
            return true;
        else
            return false;
    }
}