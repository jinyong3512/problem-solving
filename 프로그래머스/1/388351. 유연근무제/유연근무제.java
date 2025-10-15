class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        
        int answer = 0;
        
        for (int schedules_index = 0; schedules_index < n ; schedules_index++) {
            
            int tmp_schedules_hour = schedules[schedules_index] / 100;
            int tmp_schedules_minute = schedules[schedules_index] % 100;
            
            if (tmp_schedules_minute + 10 >= 60) {
                tmp_schedules_hour += 1;
                tmp_schedules_minute = tmp_schedules_minute + 10 - 60;
            } else {
                tmp_schedules_minute = tmp_schedules_minute + 10;
            }
            
            int tmp_schedules = tmp_schedules_hour * 100 + tmp_schedules_minute;
            
            boolean check = true;
            
            for (int day = 0; day < 7; day++) {
                
                if( (day + startday) % 7 == 6 || (day + startday) % 7 == 0 )
                    continue;
                
                if (timelogs[schedules_index][day] > tmp_schedules)
                    check = false;
                    
            }    
            
            if (check)
                answer++;
        }
        
        return answer;
    }
}