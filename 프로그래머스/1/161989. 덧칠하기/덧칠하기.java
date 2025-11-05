class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        for (int i = 0; i < section.length; i++) {
            answer++;
            int lastLocation = section[i] + m - 1;
            while (true) {
                i++;
                if (i >= section.length || section[i] > lastLocation)
                    break;
            }
            i--;
        }        
        return answer;
    }
}