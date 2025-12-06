class Solution {
    public int solution(String[] board) {
        int OCount = 0;
        int XCount = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'O') {
                    OCount++;
                } else if (board[i].charAt(j) == 'X') {
                    XCount++;
                }
            }
        }
        
        if (isWin(board, 'O')) {
            if (isWin(board, 'X')) {
                return 0;
            } else {
                if (OCount == XCount + 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else if (isWin(board, 'X')) {
            if (OCount == XCount)
                return 1;
            else
                return 0;
        } else {
            if (OCount == XCount) {
                return 1;
            } else {
                if (OCount == XCount + 1) {
                    return 1;
                } else {
                    return 0;
                }
            }            
        }
        

    }
    
    public boolean isWin(String[] board, char tmp) {
        
        for (int i = 0; i < board.length; i++) {            
            boolean can = true;
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) != tmp) {
                    can = false;
                }
            }
            if (can)
                return true;
        }
        
        for (int j = 0; j < board[0].length(); j++) {            
            boolean can = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i].charAt(j) != tmp) {
                    can = false;
                }
            }
            if (can)
                return true;
        }
        
        boolean can = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(i) != tmp)
                can = false;            
        }
        if (can)
            return true;
        
        can = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(2 - i) != tmp)
                can = false;            
        }
        if (can)
            return true;
        
        return false;
    }
}