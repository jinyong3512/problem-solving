import java.util.*;

class Solution {
    
    private static class VertexDistanceData {
        private final int vertexNumber;
        private final int distance;

        VertexDistanceData (int vertexNumber, int distance) {
            this.vertexNumber = vertexNumber;
            this.distance = distance;
        }

        int getVertexNumber() {
            return vertexNumber;
        }

        int getDistance() {
            return distance;
        }
    }
    
    public int solution(int n, int[][] edge) {
                       
        ArrayList<ArrayList<Integer>> graph = createGraph(n, edge);
                
        int[] distances = calculateDistances(graph, 1);
        
        int answer = calculateAnswer(distances);
        
        return answer;
    }
    
    private ArrayList<ArrayList<Integer>> createGraph(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {            
            graph.add(new ArrayList<>());
        }
        
        for (int[] e: edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        return graph;
    }
    
    private int[] calculateDistances(ArrayList<ArrayList<Integer>> graph, int startVertexNumber) {
        int[] distances = new int[graph.size()];
        for (int i = 0; i < distances.length; i++)
            distances[i] = -1;
        distances[startVertexNumber] = 0;
        
        Deque<VertexDistanceData> deque = new ArrayDeque<>();
        deque.addLast(new VertexDistanceData(1, 0));
        
        while (!deque.isEmpty()) {
            VertexDistanceData curVDD = deque.removeFirst();
            
            for (int i = 0; i < graph.get(curVDD.getVertexNumber()).size(); i++) {
                int nextVertexNumber = graph.get(curVDD.getVertexNumber()).get(i);
                
                if (distances[nextVertexNumber] != -1)
                    continue;                                
                distances[nextVertexNumber] = curVDD.getDistance() + 1;
                
                deque.addLast(new VertexDistanceData(nextVertexNumber, curVDD.getDistance() + 1));
            }
        }
        
        return distances;
    }
    
    private int calculateAnswer(int[] distances) {            
        int answer = 0;
        
        int maxDistance = 0;
        for (int distance: distances) {
            if (distance > maxDistance) {
                maxDistance = distance;
                answer = 1;
            } else if (distance == maxDistance)
                answer++;
        }
        
        return answer;
    }
}