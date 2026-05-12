class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 맨 왼쪽
        int left = 1;
        int right = stations[0] - w - 1;
        if (left <= right) {
            int length = right - left + 1;
            int range = 1 + 2 * w;

            if (length % range == 0) {
                answer += length / range;
            } else {
                answer += length / range + 1;
            }            
        }
        
        // 사이
        for (int i = 0; i < stations.length - 1; i++) {
            left = stations[i] + w + 1;
            right = stations[i + 1] - w - 1;
            if (left <= right) {
                int length = right - left + 1;
                int range = 1 + 2 * w;

                if (length % range == 0) {
                    answer += length / range;
                } else {
                    answer += length / range + 1;
                }            
            }
        }
        
        // 맨 오른쪽
        left = stations[stations.length - 1] + w + 1;
        right = n;
        if (left <= right) {
            int length = right - left + 1;
            int range = 1 + 2 * w;

            if (length % range == 0) {
                answer += length / range;
            } else {
                answer += length / range + 1;
            }            
        }
        
        return answer;
    }
}