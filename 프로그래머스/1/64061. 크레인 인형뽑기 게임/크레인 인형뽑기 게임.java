import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
            
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < moves.length; i++) {
            
            int curNumber = 0;            
            for (int y = 0; y < board.length; y++) {
                if (board[y][moves[i] - 1] != 0) {
                    curNumber = board[y][moves[i] - 1];
                    board[y][moves[i] - 1] = 0;
                    break;                    
                } 
            }
            
            if (curNumber == 0)
                continue;
            
            if (!stack.isEmpty() && stack.peek() == curNumber) {
                answer += 2;   
                stack.pop();
            } else {
                stack.push(curNumber);
            }
        }
        
        return answer;
    }
}