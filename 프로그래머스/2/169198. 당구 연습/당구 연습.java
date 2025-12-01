import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {                
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        for (int i = 0; i < balls.length; i++) {
            // 동
            int x1 = m + (m - balls[i][0]);
            int y1 = balls[i][1];
            
            // 서
            int x2 = 0 - balls[i][0];
            int y2 = balls[i][1];
            
            // 남
            int x3 = balls[i][0];
            int y3 = n + (n - balls[i][1]);            
            
            // 북
            int x4 = balls[i][0];
            int y4 = 0 - balls[i][1];
            
            int tmpAnswer = Integer.MAX_VALUE;
            
            if (startX < balls[i][0] && startY == balls[i][1]) {
            } else {
                tmpAnswer = Math.min(tmpAnswer, (int) (Math.pow(y1-startY, 2) + Math.pow(x1-startX, 2)));
            }
            
            if (startX > balls[i][0] && startY == balls[i][1]) {                
            } else {
                tmpAnswer = Math.min(tmpAnswer, (int) (Math.pow(y2-startY, 2) + Math.pow(x2-startX, 2)));
            }
            
            if (startX == balls[i][0] && startY < balls[i][1]) {                
            } else {
                tmpAnswer = Math.min(tmpAnswer, (int) (Math.pow(y3-startY, 2) + Math.pow(x3-startX, 2)));
            }
            
            if (startX == balls[i][0] && startY > balls[i][1]) {
            } else {
                tmpAnswer = Math.min(tmpAnswer, (int) (Math.pow(y4-startY, 2) + Math.pow(x4-startX, 2)));
            }
            
            arrayList.add(tmpAnswer);
                
        }
        
        int[] answer = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            answer[i] = arrayList.get(i);
        
        return answer;
    }
}