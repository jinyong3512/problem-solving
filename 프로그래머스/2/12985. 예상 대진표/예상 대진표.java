class Solution {
    public int solution(int n, int a, int b) {

        // System.out.println(Math.pow(2, 20)); // 대충 100만
        
        int answer = 0;
        
        while (true) {
            answer++;
            
            // 4 3 (o)
            // 5 4 (x)
            if (a > b && a - b == 1 && b % 2 == 1) {
                break;
            } else if (a < b && b - a == 1 && a % 2 == 1) {
                break;
            } else {
                // 1 -> 1
                // 2 -> 1
                // 3 -> 2
                // 4 -> 2
                
                // 4 -> 2 -> 1
                // 7 -> 4 -> 2
                
                if (a % 2 == 1) {
                    a = (a + 1) / 2;
                } else {
                    a = a / 2;
                }
                
                if (b % 2 == 1) {
                    b = (b + 1) / 2;
                } else {
                    b = b / 2;
                }
            }
        }

        return answer;
    }
}