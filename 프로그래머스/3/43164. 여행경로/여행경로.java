import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {        
        reSort(tickets);
        // printTickets(tickets);
        
        String[] answer = calculateAnswer(tickets);
        return answer;
    }
    
    private void reSort(String[][] tickets) {
        Arrays.sort(tickets, new Comparator<>(){
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[1].compareTo(o2[1]) < 0) {
                    return -1;
                } else if (o1[1].compareTo(o2[1]) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
    
    private void printTickets(String[][] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            System.out.println(tickets[i][1]);
        }
    }
    
    private String[] calculateAnswer(String[][] tickets) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast("ICN");
        
        boolean[] visited = new boolean[tickets.length];
        
        dfs(tickets, deque, visited, 0);
        
        String[] answer = new String[deque.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = deque.removeFirst();
        }
        
        return answer;
    }
    
    private boolean dfs(String[][] tickets, Deque<String> deque, boolean[] visited, int count) {
        if (count == tickets.length) {
            return true;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!tickets[i][0].equals(deque.peekLast())) {
                continue;
            }
            if (visited[i]) {
                continue;
            }
            
            deque.addLast(tickets[i][1]);
            visited[i] = true;
            if (dfs(tickets, deque, visited, count + 1))
                return true;
            deque.removeLast();
            visited[i] = false;
        }
        
        return false;
    }
}