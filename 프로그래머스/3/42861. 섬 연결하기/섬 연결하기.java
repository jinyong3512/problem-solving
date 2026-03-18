// 0+1 0+2
// 0+1 -> 0+1+5 0+1+1
// 0+2 0+1+1 0+1+5
// 0+2 -> 

import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {        
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < costs.length; i++) {
            int v1 = costs[i][0];
            int v2 = costs[i][1];
            int weight = costs[i][2];
            
            graph.get(v1).add(new Edge(v2, weight));
            graph.get(v2).add(new Edge(v1, weight));
        }
        
        PriorityQueue<Data> pQ = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.weight > o2.weight) {
                    return 1;
                } else if (o1.weight == o2.weight) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        
        boolean[] visited = new boolean[n];
        pQ.add(new Data(0, 0));        
        int answer = 0;
        
        while (!pQ.isEmpty()) {
            Data curData = pQ.remove();
            
            if (visited[curData.curVertex]) {
                continue;
            }            
            visited[curData.curVertex] = true;
            answer += curData.weight;
            
            for (int i = 0; i < graph.get(curData.curVertex).size(); i++) {
                Edge curEdge = graph.get(curData.curVertex).get(i);
                
                pQ.add(new Data(curEdge.toVertex, curEdge.weight));
            }
        }
        
        return answer;
    }    
    
    class Edge {
        int toVertex;
        int weight;
        
        Edge (int toVertex, int weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }
    
    class Data {
        int curVertex;
        int weight;
        
        Data (int curVertex, int weight) {
            this.curVertex = curVertex;
            this.weight = weight;
        }
    }
}