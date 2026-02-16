import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        // int answer = 0;
        
        int[] numbers = new int[4];
        numbers[0] = a;
        numbers[1] = b;
        numbers[2] = c;
        numbers[3] = d;
        
        Arrays.sort(numbers);
        
        if (numbers[0] == numbers[3]) {
            return 1111 * numbers[0];
        } else if (numbers[0] == numbers[2] && numbers[2] != numbers[3]) {
            return (int)Math.pow(10 * numbers[0] + numbers[3], 2);
        } else if (numbers[1] == numbers[3] && numbers[0] != numbers[1]) {
            return (int)Math.pow(10 * numbers[1] + numbers[0], 2);
        } else if (numbers[0] == numbers[1] && numbers[2] == numbers[3] && numbers[1] != numbers[2]) {
            return (numbers[1] + numbers[2]) * Math.abs(numbers[1] - numbers[2]);
        } else if (numbers[0] != numbers[1] && numbers[1] != numbers[2] && numbers[2] == numbers[3]) {
            return numbers[0] * numbers[1];
        } else if (numbers[0] != numbers[1] && numbers[2] != numbers[3] && numbers[1] == numbers[2]) {
            return numbers[0] * numbers[3];
        } else if (numbers[1] != numbers[2] && numbers[2] != numbers[3] && numbers[0] == numbers[1]) {
            return numbers[2] * numbers[3];
        } else {
            return numbers[0];
        }
    }
}