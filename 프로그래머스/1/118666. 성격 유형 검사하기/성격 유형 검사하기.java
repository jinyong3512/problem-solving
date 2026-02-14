class Solution {
    
    public static String[][] kinds = new String[][]{
        {"RT", "TR"}, 
        {"FC", "CF"}, 
        {"MJ", "JM"}, 
        {"AN", "NA"}
    };
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        int[] scores = new int[4];
        
        for (int i = 0; i < survey.length; i++) {
            int findY = -1;
            int findX = -1;
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 2; x++) {
                    if (survey[i].equals(kinds[y][x])) {
                        findY = y;
                        findX = x;
                    }
                        
                }
            }
            
            int score = 0;
            if (findX == 0) {
                if (choices[i] == 1) {
                    score = 3;
                } else if (choices[i] == 2) {
                    score = 2;
                } else if (choices[i] == 3) {
                    score = 1;
                } else if (choices[i] == 4) {
                    score = 0;
                } else if (choices[i] == 5) {
                    score = -1;
                } else if (choices[i] == 6) {
                    score = -2;
                } else if (choices[i] == 7) {
                    score = -3;
                }
            } else {
                if (choices[i] == 1) {
                    score = -3;
                } else if (choices[i] == 2) {
                    score = -2;
                } else if (choices[i] == 3) {
                    score = -1;
                } else if (choices[i] == 4) {
                    score = 0;
                } else if (choices[i] == 5) {
                    score = 1;
                } else if (choices[i] == 6) {
                    score = 2;
                } else if (choices[i] == 7) {
                    score = 3;
                }                
            }
            
            scores[findY] += score;
            
        }
        
        if (scores[0] >= 0) {
            answer += "R";
        } else {
            answer += "T";  
        }
        if (scores[1] > 0) {
            answer += "F";
        } else {
            answer += "C";  
        }
        if (scores[2] > 0) {
            answer += "M";
        } else {
            answer += "J";  
        }
        if (scores[3] >= 0) {
            answer += "A";
        } else {
            answer += "N";  
        }
        
        
        return answer;
    }
}