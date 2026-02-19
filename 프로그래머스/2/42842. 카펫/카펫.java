class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        // 가로길이, 세로길이
        // 8 1 -> 3, 3
        // 10 2 -> 4, 3
        
        for (int width = 3; width < brown / 2; width++) {
            for (int height = 3; height < brown / 2; height++) {
                int brownCount = (width * 2) + (height - 2) * 2;
                int yellowCount = (width - 2) * (height - 2);
                
                if (brownCount == brown && yellowCount == yellow)
                    answer = new int[]{width, height};
            }
        }
        
        return answer;
    }
}