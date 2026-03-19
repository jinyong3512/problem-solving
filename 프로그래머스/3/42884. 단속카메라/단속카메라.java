import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                } else {
                    if (o1[0] < o2[0]) {
                        return -1;
                    } else if (o1[0] > o2[0]) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        
        // for (int[] value: routes) {
        //     System.out.printf("%d %d\n", value[0], value[1]);
        // }
                
        int answer = 0;
        
        for (int i = 0; i < routes.length; i++) {
            int point = routes[i][1];
            answer++;
            
            while (true) {
                i++;
                if (i == routes.length) {
                    break;
                }
                
                if (routes[i][0] <= point && routes[i][1] >= point) {
                    continue;
                } else {
                    i--;
                    break;
                }
            }
        }

        return answer;
    }
}