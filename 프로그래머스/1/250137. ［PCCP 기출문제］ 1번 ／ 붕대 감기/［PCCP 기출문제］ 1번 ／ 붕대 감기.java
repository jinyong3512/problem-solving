class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        return function1(bandage, health, attacks);
    }
    
    public static int function1(int[] bandage, int health, int[][] attacks) {
        int nowHealth = health;
        int lastattackTime = 0;
        
        for (int i = 0; i < attacks.length; i++) {
            int curattackTime = attacks[i][0];
            
            int hillTime = (curattackTime-1) - lastattackTime;
            
            nowHealth += hillTime * bandage[1];
            nowHealth += (hillTime/bandage[0]) * bandage[2];
            
            nowHealth = Math.min(nowHealth, health);
            
            nowHealth -= attacks[i][1];
            if (nowHealth <= 0)
                return -1;
            
            lastattackTime = curattackTime;
            System.out.println(nowHealth);
        }
        return nowHealth;
    }
}