import java.util.*;

class Solution {
    private static final int OUT = 1;
    private static final int IN =  0;
    
    private static class Edge {                
        private final int toVertexNumber;
        private final int direction;
        
        Edge (int toVertexNumber, int direction) {
            this.toVertexNumber = toVertexNumber;
            this.direction = direction;
        }
        
        int getToVertexNumber() {
            return toVertexNumber;
        }
        int getDirection() {
            return direction;
        }
    }
    
    public int solution(int n, int[][] results) {
        
        List<List<Edge>> graph = createGraph(n, results);
        int[][] inOutCounts = calculateinOutCounts(graph);
        // printInOutCounts(inOutCounts);        
        int answer = calculateAnswer(inOutCounts);
        return answer;
    }
    
    private List<List<Edge>> createGraph(int n, int[][] results) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < results.length; i++) {
            graph.get(results[i][0]).add(new Edge(results[i][1], OUT));
            graph.get(results[i][1]).add(new Edge(results[i][0], IN));
        }
        
        return graph;
    }
    
    private int[][] calculateinOutCounts(List<List<Edge>> graph) {
        int[][] inOutCounts = new int[graph.size()][2];
        for (int i = 1; i < graph.size(); i++) {
            
            boolean[] visited = new boolean[graph.size()];
            dfs(graph, inOutCounts, IN, visited, i, i);
            
            visited = new boolean[graph.size()];
            dfs(graph, inOutCounts, OUT, visited, i, i);
        }
        return inOutCounts;
    }
    
    private void dfs(List<List<Edge>> graph, int[][] inOutCounts, int direction, boolean[] visited, int startVertexNumber, int curVertexNumber) {
        visited[curVertexNumber] = true;    
        
        for (int i = 0; i < graph.get(curVertexNumber).size(); i++) {
            Edge curEdge = graph.get(curVertexNumber).get(i);
            if (curEdge.getDirection() != direction)
                continue;
            
            if (visited[curEdge.getToVertexNumber()])
                continue;
            
            inOutCounts[startVertexNumber][direction]++;
            dfs(graph, inOutCounts, direction, visited, startVertexNumber, curEdge.getToVertexNumber());
        }
    }
    
    private void printInOutCounts(int[][] inOutCounts) {
        for (int i = 1; i < inOutCounts.length; i++) {
            System.out.printf("i: %d, OUT: %d, IN: %d\n",i , inOutCounts[i][OUT], inOutCounts[i][IN]);
        }
    }
    
    private int calculateAnswer(int[][] inOutCounts) {
        int answer = 0;
        for (int i = 1; i < inOutCounts.length; i++) {
            if (inOutCounts.length - 2 == inOutCounts[i][OUT] + inOutCounts[i][IN])
                answer++;
        }
        return answer;
    }
    
}