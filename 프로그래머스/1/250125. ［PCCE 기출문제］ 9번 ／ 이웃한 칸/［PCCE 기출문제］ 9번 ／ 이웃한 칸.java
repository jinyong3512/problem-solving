class Solution {
    public int solution(String[][] board, int h, int w) {
        
        // 1.
        int n = board.length;        
        
        // 2.
        int count = 0;
        
        // 3.
        int[] dh = new int[]{0, 1, -1, 0};
        int[] dw = new int[]{1, 0, 0, -1};
        
        // 4.
        for (int i = 0; i <= 3; i++) {
            int h_check = h + dh[i];
            int w_check = w + dw[i];

            if (h_check >= 0 && h_check < n && w_check >= 0 && w_check < n){
                if (board[h][w].equals(board[h_check][w_check]))
                    count++;
            }
        }                
            
        return count;
        
    }
}