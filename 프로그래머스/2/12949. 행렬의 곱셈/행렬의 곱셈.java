class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
                
        // 3x2 2x2 ->  3x2
        // axb bxc -> axc
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[0].length; j++) {
                for (int depth = 0; depth < arr1[0].length; depth++) {
                    answer[i][j] += arr1[i][depth] * arr2[depth][j];
                }
            }
        }
        
        return answer;
    }
}