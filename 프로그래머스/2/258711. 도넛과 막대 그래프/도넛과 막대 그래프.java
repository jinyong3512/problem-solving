import java.util.*;

class Edge {
    int to;
    Edge (int to) {
        this.to = to;
    }
}

class Solution {
    public int[] solution(int[][] edges) {
        
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        
        int maxVertexNumber = 0;
        
        for (int edgesIndex = 0; edgesIndex < edges.length; edgesIndex++) {
            maxVertexNumber = Math.max(maxVertexNumber, edges[edgesIndex][0]);
            maxVertexNumber = Math.max(maxVertexNumber, edges[edgesIndex][1]);
        }
        
        for (int i = 0; i <= maxVertexNumber; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int edgesIndex = 0; edgesIndex < edges.length; edgesIndex++) {
            int from = edges[edgesIndex][0];
            int to = edges[edgesIndex][1];            
            graph.get(from).add(new Edge(to));
        }
        
        // System.out.printf("graph.size(): %d\n", graph.size());
        
        int startVertex = 0;
        int[] ins = new int[graph.size()];
        int[] outs = new int[graph.size()];
        for (int i = 0; i < edges.length; i++) {
            outs[edges[i][0]]++;
            ins[edges[i][1]]++;
        }
        
        for (int i = 1; i < graph.size(); i++) {
            if (ins[i] == 0 && outs[i] >= 2)
                startVertex = i;
        }
        
        // System.out.printf("startVertex: %d\n", startVertex);
        
        int[] answer = new int[4];
        answer[0] = startVertex;    
        
        boolean[] visited = new boolean[graph.size()];
        
        for (int i = 0; i < graph.get(startVertex).size(); i++) {
            answer[check(graph, graph.get(startVertex).get(i).to, visited)]++;
        }
        
        return answer;
    }
    
    public int check(ArrayList<ArrayList<Edge>> graph, int startVertex, boolean[] visited) { 
        Queue<Integer> queue = new LinkedList<>();
        
        visited[startVertex] = true;
        queue.add(startVertex);
        
        int vertexCount = 1;
        int edgeCount = 0;
        
        while (!queue.isEmpty()) {
            int curVertex = queue.remove();
            
            for (int i = 0; i < graph.get(curVertex).size(); i++) {
                int nextVertex = graph.get(curVertex).get(i).to;
                
                edgeCount++;
                
                if (visited[nextVertex])
                    continue;
                
                visited[nextVertex] = true;
                queue.add(nextVertex);
                vertexCount++;
            }
        }
        
        // System.out.printf("startVertex: %d, vertexCount: %d, edgeCount: %d\n", startVertex, vertexCount, edgeCount);
        // n n
        // n n-1
        // 2n+1 2n+2
        
        if (vertexCount == edgeCount)
            return 1;
        else if (vertexCount == edgeCount + 1)
            return 2;
        else if ((vertexCount - 1) / 2  == (edgeCount - 2) / 2)
            return 3;
        else
            return -1;
    }
}