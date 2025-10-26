class Solution {
    public int solution(int[] players, int m, int k) {
        
        int[] activeHistory = new int[24];
        int active = 0;
        int x = 0;
        
        for (int i = 0; i < players.length; i++) {
            if (i - k >= 0) 
                active -= activeHistory[i - k];
            
            if (players[i] / m > active) {
                int needMoreServer = players[i] / m - active;
                activeHistory[i] = needMoreServer;
                active += needMoreServer;  
                x += needMoreServer;
            }
        }
        
        return x;
    }
}