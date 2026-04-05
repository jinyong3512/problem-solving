class Solution {
    public int[] solution(int n, long left, long right) {        
        int[] answer = new int[(int)(right - left) + 1];
        
        // 10^7 = 10000000
        
        int q1 = (int)(left / n);
        int r1 = (int)(left % n);
        int q2 = (int)(right / n);
        int r2 = (int)(right % n);
        // System.out.printf("%d %d %d %d\n", q1, r1, q2, r2);
        
        int answerIndex = 0;
        
        if (q1 == q2) {
            for (int x = r1; x <= r2; x++) {
                answer[answerIndex] = findValue(q1, x);
                answerIndex++;
            }
        } else {
            // left 채우기
            for (int i = r1; i < n; i++) {
                answer[answerIndex] = findValue(q1, i);
                answerIndex++;
            }        

            // 가운데 채우기
            for (int i = q1 + 1; i < q2; i++) {
                for (int j = 0; j < n; j++) {
                    answer[answerIndex] = findValue(i, j);
                    answerIndex++;
                }
            }

            // right 채우기
            for (int i = 0; i <= r2; i++) {
                answer[answerIndex] = findValue(q2, i);
                answerIndex++;
            }            
        }
        
        return answer;
    }
    
    private static int findValue(int y, int x) {
        int value = 0;
        
        value += (y + 1);
        
        if (x > y) {
            value += (x - y);
        }
        
        return value;
    } 
}