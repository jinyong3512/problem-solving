class Solution {
    public int solution(String s) {
        int answer = 0;
        
        int tmpCount1 = 0;
        int tmpCount2 = 0;
        char curX = '\0';
        
        for (int i = 0; i < s.length(); i++) {
            if (curX == '\0') {
                curX = s.charAt(i);
                tmpCount1++;
            } else {
                
                if (s.charAt(i) == curX) {
                    tmpCount1++;
                } else {
                    tmpCount2++;
                }
                
                if (tmpCount1 == tmpCount2) {
                    answer++;
                    tmpCount1 = 0;
                    tmpCount2 = 0;
                    curX = '\0';
                }
                
            }
        }
        
        if (tmpCount1 != tmpCount2) {
            answer++;
        }        
        
        
        return answer;
    }
}