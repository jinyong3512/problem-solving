import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else if (o1 == o2) {
                    return 0;
                } else {
                    return -1;
                }
            }                
        });
        
        for (int i = 0; i < operations.length; i++) {
            StringTokenizer st = new StringTokenizer(operations[i]);
            String order = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            
            if (order.equals("I")) {
                minPQ.add(number);
                maxPQ.add(number);
            } else if (order.equals("D")) {
                if (minPQ.isEmpty()) {
                    continue;
                }
                
                if (number == 1) {
                    minPQ.remove(maxPQ.remove());
                } else if (number == -1) {
                    maxPQ.remove(minPQ.remove());
                }
            }
        }
        
        int minValue = 0;
        int maxValue = 0;
        
        if (!minPQ.isEmpty()) {
            minValue = minPQ.peek();
        }
        if (!maxPQ.isEmpty()) {
            maxValue = maxPQ.peek();
        }
        
        return new int[]{maxValue, minValue};
    }
}