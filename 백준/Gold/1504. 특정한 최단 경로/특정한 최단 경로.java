import java.io.*;
import java.util.*;

class Edge {
    int startVertex;
    int endVertex;
    int weight;

    Edge(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }
}

class Vertex {
    int number;
    int weight;

    Vertex(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, E;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        int v1, v2;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(a, b, c));
            graph.get(b).add(new Edge(b, a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////

        long answer1 = go(graph, 1, v1, v2, N);
        long answer2 = go(graph, 1, v2, v1, N);

        if(answer1 == Long.MAX_VALUE && answer2 == Long.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(Math.min(answer1,answer2));
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

        for (int i = 1; i < graph.size(); i++)
            distances[i] = Long.MAX_VALUE;

        pQ.add(new Vertex(startVertex, 0));

        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.number])
                continue;

            visited[curVertex.number] = true;
            distances[curVertex.number] = curVertex.weight;

            for (int i = 0; i < graph.get(curVertex.number).size(); i++) {
                Edge curEdge = graph.get(curVertex.number).get(i);

                pQ.add(new Vertex(curEdge.endVertex, curVertex.weight + curEdge.weight));
            }
        }

        return distances;


    }

    public static long go(ArrayList<ArrayList<Edge>> graph, int start, int v1, int v2, int end) {
        long answer = 0;

        long[] distances = dijkstra(graph, start);
        if (distances[v1] == Long.MAX_VALUE) {
            return distances[v1];
        }
        answer += distances[v1];


        distances = dijkstra(graph, v1);
        if (distances[v2] == Long.MAX_VALUE) {
            return distances[v2];
        }
        answer += distances[v2];

        distances = dijkstra(graph, v2);
        if (distances[end] == Long.MAX_VALUE) {
            return distances[end];
        }
        answer += distances[end];

        return answer;
    }
}