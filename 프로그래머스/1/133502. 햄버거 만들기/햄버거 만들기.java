import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < ingredient.length; i++) {
            if (ingredient[i] == 1) {
                
                if (!stack.isEmpty() && stack.peek() == 3) {
                    
                    int tmp1 = stack.pop();
                    if (!stack.isEmpty() && stack.peek() == 2) {
                        
                        int tmp2 = stack.pop();
                        if(!stack.isEmpty() && stack.peek() == 1) {
                            stack.pop();
                            answer++;
                        } else {
                            stack.push(tmp2);
                            stack.push(tmp1);
                            stack.push(ingredient[i]);
                        }
                        
                        
                    } else {
                        stack.push(tmp1);
                        stack.push(ingredient[i]);
                    }  
                    
                } else {
                    stack.push(ingredient[i]);
                }
            
            } else {
                stack.push(ingredient[i]);
            }
        }
        
        
        return answer;
    }
}