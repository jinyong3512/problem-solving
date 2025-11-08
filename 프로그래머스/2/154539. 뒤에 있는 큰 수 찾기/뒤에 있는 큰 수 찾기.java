import java.util.*;

class Data{
    int number, index;
    Data (int number, int index) {
        this.number = number;
        this.index = index;
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<Data> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && stack.peek().number < numbers[i]) {
                Data curData = stack.pop();
                answer[curData.index] = numbers[i];
            }
            stack.push(new Data(numbers[i], i));
        }
        
        while (!stack.isEmpty()) {
                Data curData = stack.pop();
                answer[curData.index] = -1;
            }
        
        return answer;
    }
}