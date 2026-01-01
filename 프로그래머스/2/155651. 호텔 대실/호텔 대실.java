import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[][] bookTimes = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            int hour, minute;
            
            hour = Integer.parseInt(book_time[i][0].substring(0, 2));
            minute = Integer.parseInt(book_time[i][0].substring(3, 5));            
            hour *= 60;            
            bookTimes[i][0] = hour + minute;
            
            hour = Integer.parseInt(book_time[i][1].substring(0, 2));
            minute = Integer.parseInt(book_time[i][1].substring(3, 5));            
            hour *= 60;            
            bookTimes[i][1] = hour + minute;            
        }
        
        Arrays.sort(bookTimes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] > b[0])
                    return 1;
                else if (a[0] == b[0])
                    return 0;
                else
                    return -1;
            }
        });
        
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        
        for (int i = 0; i < bookTimes.length; i++) {
            while (!pQ.isEmpty()) {
                if(pQ.peek() + 10 <= bookTimes[i][0])
                    pQ.remove();
                else
                    break;
            }
            
            pQ.add(bookTimes[i][1]);
            
            answer = Math.max(answer, pQ.size());
        }
        
        
        
        return answer;
    }
}