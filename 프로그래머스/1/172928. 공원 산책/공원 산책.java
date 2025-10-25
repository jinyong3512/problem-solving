class Solution {
    public int[] solution(String[] park, String[] routes) {
        
        int curY = -1;
        int curX = -1;
        
        for (int i = 0 ; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    curY = i;
                    curX = j;
                }
            }
        }
        
        for (int i = 0; i < routes.length; i++) {
            char op =  routes[i].charAt(0);
            int n = routes[i].charAt(2) - '0';
            
            int newY = curY;
            int newX = curX;
            
            if (op == 'N')
                newY -= n;
            else if (op == 'S')
                newY += n;
            else if (op == 'W')
                newX -= n;
            else if (op == 'E')
                newX += n;
            
            boolean can = true;
            
            if (newY < 0 || newY >= park.length || newX < 0 || newX >= park[0].length())
                continue;
            
            for (int y = Math.min(curY, newY); y <= Math.max(curY, newY); y++) {
                if (park[y].charAt(curX) == 'X')
                    can = false;
            }
            for (int x = Math.min(curX, newX); x <= Math.max(curX, newX); x++) {
                if (park[curY].charAt(x) == 'X')
                    can = false;
            }
            
            if (can) {
                curY = newY;
                curX = newX;
            }
        }
        
        
        return new int[]{curY, curX};
    }
}