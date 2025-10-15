import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        StringBuilder sb = new StringBuilder();
        
        int now = convert_string_to_int(pos);
        
        for (int i = 0; i < commands.length; i++) {
            
            // 오프닝 건너뛰기
            if (convert_string_to_int(op_start) <= now && now <= convert_string_to_int(op_end))
                now = convert_string_to_int(op_end);
            
            if (commands[i].equals("prev")) {
                now -= 10;
                
                // now: 2 -> -8 -> 0
                if (now < 0) 
                    now = 0;
                
            } else {
                now += 10;
                
                // now: 
                if (now > convert_string_to_int(video_len))
                    now = convert_string_to_int(video_len);
            }
            
            // System.out.println(now);
        }
        
        // 오프닝 건너뛰기
        if (convert_string_to_int(op_start) <= now && now <= convert_string_to_int(op_end))
            now = convert_string_to_int(op_end);    
        
        sb.append(now/60/10);
        sb.append(now/60%10);
        sb.append(":");
        sb.append(now%60/10);
        sb.append(now%60%10);
        
        return sb.toString();
    }
    
    public int convert_string_to_int (String str) {
        int str_mm = Integer.parseInt(str.substring(0,2));
        int str_ss = Integer.parseInt(str.substring(3,5));
        
        return str_mm * 60 + str_ss;
    }
}