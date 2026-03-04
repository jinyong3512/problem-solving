import java.util.*;

class Data {
    int vertexNumber;
    int depth;
    Data (int vertexNumber, int depth) {
        this.vertexNumber = vertexNumber;
        this.depth = depth;
    }
}

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // Create graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            ArrayList<Integer> tmpArrayList = new ArrayList<>();
            graph.add(tmpArrayList);
        }
        
        for (int i = 0; i < edge.length; i++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        
        // 
        int[] depths = new int[n + 1];
        
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        
        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(1, 0));
        
        while (!queue.isEmpty()) {
            Data curData = queue.remove();
            
            for (int i = 0; i < graph.get(curData.vertexNumber).size(); i++) {
                int nextVertexNumber = graph.get(curData.vertexNumber).get(i);
                
                if (visited[nextVertexNumber])
                    continue;
                
                visited[nextVertexNumber] = true;
                depths[nextVertexNumber] = curData.depth + 1;
                
                queue.add(new Data(nextVertexNumber, curData.depth + 1));
            }
        }
        
        // for (int i = 1; i <= n; i++) {
        //     System.out.printf("%d ", depths[i]);
        // }
        
        int maxDistance = 0;
        for (int i = 2; i <= n; i++) {
            if (maxDistance > depths[i])
                continue;
            else if (maxDistance == depths[i])
                answer++;
            else {
                answer = 1;
                maxDistance = depths[i];
            }
        }
        
        return answer;
    }
}