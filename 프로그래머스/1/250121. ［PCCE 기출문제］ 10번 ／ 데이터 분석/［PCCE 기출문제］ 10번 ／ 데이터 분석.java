import java.io.*;
import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        ArrayList<int[]> newArrayList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            int idx = -1;
            if (ext.equals("code"))
                idx = 0;
            else if (ext.equals("date"))
                idx = 1;
            else if (ext.equals("maximum"))
                idx = 2;
            else
                idx = 3;
            
            if (data[i][idx] < val_ext)
                newArrayList.add(data[i]);
        }    
        
        newArrayList.sort(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                
                int idx = -1;
                if (sort_by.equals("code"))
                    idx = 0;
                else if (sort_by.equals("date"))
                    idx = 1;
                else if (sort_by.equals("maximum"))
                    idx = 2;
                else
                    idx = 3;
                
                if (a[idx] < b[idx])
                    return -1;
                else if (a[idx] == b[idx])
                    return 0;
                else
                    return 1;
            }
        });
        
        int[][] answer = new int[newArrayList.size()][4];
        
        for (int i = 0; i < newArrayList.size(); i++)
            answer[i] = newArrayList.get(i);
        
        return answer;
    }
}