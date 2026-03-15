import java.util.*;

class Solution {
    private static final char[] ALPHAS = new char[]{'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        List<String> wholeWords = new ArrayList<>();                
        dfs(wholeWords, new StringBuilder());
        
        Collections.sort(wholeWords);
        
        int answer = 0;
        for (int i = 0; i < wholeWords.size(); i++) {
            if (wholeWords.get(i).equals(word)) {
                answer = i + 1;
            }
        }
        
        
        return answer;
    }

    private void dfs(List<String> wholeWords, StringBuilder sb) {
        if (sb.length() == ALPHAS.length) {
            return;
        }
        
        for (int i = 0; i < ALPHAS.length; i++) {
            sb.append(ALPHAS[i]);
            wholeWords.add(sb.toString());
            dfs(wholeWords, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}