class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        int[] scores = new int[3];
        char[] bonuses = new char[3];
        char[] options = new char[3];
        
        int lastIndex = 0;
        int gamesIndex = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            if (dartResult.charAt(i) == 'S' || dartResult.charAt(i) == 'D' || dartResult.charAt(i) == 'T') {
                if (i + 1 < dartResult.length() && 
                    (dartResult.charAt(i + 1) == '*' || dartResult.charAt(i + 1) == '#')
                   ) {
                    scores[gamesIndex] = Integer.parseInt(dartResult.substring(lastIndex, i));
                    bonuses[gamesIndex] = dartResult.charAt(i);
                    options[gamesIndex] = dartResult.charAt(i + 1);
                    gamesIndex++;
                    lastIndex = i + 2;
                } else {
                    scores[gamesIndex] = Integer.parseInt(dartResult.substring(lastIndex, i));
                    bonuses[gamesIndex] = dartResult.charAt(i);
                    //options[gamesIndex] = dartResult.charAt(i + 1);
                    gamesIndex++;
                    lastIndex = i + 1;
                }
            }
        }
        
        for ( int i = 0; i < 3; i++) {
            System.out.printf("%d %c %c\n", scores[i], bonuses[i], options[i]);
        }
        
        int[] answers = new int[3];
        for (int i = 0; i < 3; i++) {
            if (bonuses[i] == 'S')
                answers[i] = (int)Math.pow(scores[i], 1);            
            else if (bonuses[i] == 'D')
                answers[i] = (int)Math.pow(scores[i], 2);
            else if (bonuses[i] == 'T')
                answers[i] = (int)Math.pow(scores[i], 3);
        }
        
        for (int i = 0; i < 3; i++) {
            if (options[i] == '\0') { 
                
            } else if (options[i] == '*') {
                answers[i] *= 2;
                if (i != 0) {
                    answers[i -1] *= 2;
                }
            } else if (options[i] == '#'){
                answers[i] *= -1;
            }
            
        }
        
        for (int i = 0; i < 3; i++)
            answer += answers[i];
        
        return answer;
    }
}