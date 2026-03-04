import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        words = changeWords(begin, words);
        List<List<Integer>> graph = createGraph(begin, words);
        // printGraph(graph);
        
        int answer = bfs(target, words, graph);        
        return answer;
    }
    
    private String[] changeWords(String begin, String[] words) {
        String[] tmpWords = new String[words.length + 1];
        tmpWords[0] = begin;
        for (int i = 0; i < words.length; i++) {
            tmpWords[i + 1] = words[i];
        }
        return tmpWords;       
    }
    
    private List<List<Integer>> createGraph(String begin, String[] words) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            graph.add(new ArrayList<>());
            
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                
                int diffCount = 0;
                for (int k = 0; k < words[j].length(); k++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) {
                        diffCount++;
                    }
                }
                if (diffCount == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        
        return graph;
    }
    
    private void printGraph(List<List<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.printf("i: %d ", i);
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.printf("%d ", graph.get(i).get(j));
            }
            System.out.println();
        }
    }
    
    private int bfs(String target, String[] words, List<List<Integer>> graph) {
        boolean[] visited = new boolean[words.length];
        visited[0] = true;
        
        Queue<Data> queue = new ArrayDeque<>();
        queue.add(new Data(0, 0));
        
        while (!queue.isEmpty()) {
            Data curData = queue.remove();
            
            if (words[curData.getIndex()].equals(target))
                return curData.getDepth();
            
            for (int i = 0; i < graph.get(curData.getIndex()).size(); i++) {
                int nextVertexNumber = graph.get(curData.getIndex()).get(i);
                
                if (visited[nextVertexNumber]) {
                    continue;
                }
                
                visited[nextVertexNumber] = true;
                queue.add(new Data(nextVertexNumber, curData.getDepth() + 1));
            }
        }
        
        return 0;
    }
    
    private static class Data {
        private final int index;
        private final int depth;
        
        Data(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
        
        int getIndex() {
            return index;
        }
        int getDepth() {
            return depth;
        }
    }
}