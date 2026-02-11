class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        for (int i = 0; i + p.length() <= t.length(); i++) {
            long tmp = Long.parseLong(t.substring(i, i + p.length()));
            if (Long.parseLong(p) >= tmp)
                answer++;
        }
        
        return answer;
    }
}