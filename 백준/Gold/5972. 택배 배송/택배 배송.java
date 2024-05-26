import java.io.*;
import java.util.*;

class Edge {
    int endVertex;
    int weight;

    Edge(int endVertex, int weight) {
        this.endVertex = endVertex;
        this.weight = weight;
    }
}

class Vertex{
    int number;
    int weight;

    Vertex(int number, int weight){
        this.number = number;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 19:43

        // N개의 헛간 5*10^4
        // M개의 소들의 길 5*10^4
        // 각각의 길에 C_i 마리의 소가 있다

        // 두개의 헛간은 ㅎ나 이상의 길로 되어 있을 수 있다
        // 1 에서 N으로 가자

        // A에서 B로 가는 최소 비용을 구하는 문제네

        int N, M;
        int A, B, C;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            A--;
            B--;

            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));
        }

        //////////////////////////////////////////////

        long[] distances = dijkstra(graph,0);

        System.out.println(distances[graph.size()-1]);

    }

    public static long[] dijkstra(ArrayList<ArrayList<Edge>> graph, int startVertex) {
        long[] distances = new long[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<Vertex> pQ = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {

                if (o1.weight < o2.weight)
                    return -1;
                else if (o1.weight == o2.weight)
                    return 0;
                else
                    return 1;
            }
        });

        for (int i = 0; i < distances.length; i++)
            distances[i] = Long.MAX_VALUE;
        pQ.add(new Vertex(startVertex,0));

        while(!pQ.isEmpty()){
            Vertex curVertex = pQ.remove();

            if(visited[curVertex.number])
                continue;

            visited[curVertex.number]=true;
            distances[curVertex.number] = curVertex.weight;
            for(int i =0 ; i < graph.get(curVertex.number).size(); i++){

                int newVertexNumber = graph.get(curVertex.number).get(i).endVertex;
                int newWeight = graph.get(curVertex.number).get(i).weight + curVertex.weight;

                pQ.add(new Vertex(newVertexNumber,newWeight));

            }
        }

        return distances;
    }
}
