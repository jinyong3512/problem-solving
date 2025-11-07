class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        
        int cards1Index = 0;
        int cards2Index = 0;
        
        boolean can = true;
        for (int i = 0; i < goal.length; i++) {
            if (cards1Index < cards1.length && cards1[cards1Index].equals(goal[i])) {
                cards1Index++;
                continue;
            }
            if (cards2Index < cards2.length && cards2[cards2Index].equals(goal[i])) {
                cards2Index++;
                continue;
            }
            can = false;            
        }
                
        if (can)
            return "Yes";
        else
            return "No";
    }
}