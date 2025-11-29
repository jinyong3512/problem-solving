class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        // 1분 = 60초
        // 1시간 = 3600초
        // 24시간 = 3600초 * 24 = 	86400s 43200
        
        double startTime = h1 * 3600 + m1 * 60 + s1;
        double endTime = h2 * 3600 + m2 * 60 + s2;        
        
        for (double curTime = startTime + 0.001; curTime <= endTime + 0.001; curTime += 0.001) {
            // 0 ~ 0.99999999    
            
            double prevH = (curTime - 0.001) % 43200 / 43200; // 시침 각도
            double prevM = (curTime - 0.001) % 3600 / 3600; // 분침 각도
            double prevS = (curTime - 0.001) % 60 / 60; // 분침 각도
            
            double curH = curTime % 43200 / 43200; // 시침 각도
            double curM = curTime % 3600 / 3600; // 분침 각도
            double curS = curTime % 60 / 60; // 분침 각도
            
            if (curH - prevH < 0)
                curH++;
            if (curM - prevM < 0)
                curM++;
            if (curS - prevS < 0){
                curS++;
            }
            
            boolean can = false;
            if (prevS <= prevH && curS >= curH) {
                can = true;
            }
            if (prevS <= prevM && curS >= curM) {
                can = true;
            }
            
            if(can)
                answer++;
            
        }
        
        
        
        return answer;
    }
}