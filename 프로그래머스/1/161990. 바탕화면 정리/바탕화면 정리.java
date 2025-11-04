class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {};
        
        int luy = -1;
        int lux = -1;        
        int rdy = -1;
        int rdx = -1;
        
        for (int i = 0; i < wallpaper.length; i++) {
            boolean find = false;
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    find = true;
                }
            }
            if (find){
                lux = i;
                break;
            }
        }
        
        for (int i = wallpaper.length - 1; i >= 0; i--) {
            boolean find = false;
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    find = true;
                }
            }
            if (find) {
                rdx = i;
                break;
            }
        }    
        
        for (int j = 0; j < wallpaper[0].length(); j++) {
            boolean find = false;
            for (int i = 0; i < wallpaper.length; i++) {
                if (wallpaper[i].charAt(j) == '#') {
                    find = true;
                }
            }
            if (find) {
                luy = j;
                break;
            }
        }
        
        for (int j = wallpaper[0].length() - 1; j >= 0; j--) {
            boolean find = false;
            for (int i = 0; i < wallpaper.length; i++) {
                if (wallpaper[i].charAt(j) == '#') {
                    find = true;
                }
            }
            if (find) {
                rdy = j;
                break;
            }
        }        
        
        
        return new int[]{lux, luy, rdx + 1, rdy + 1};
    }
}