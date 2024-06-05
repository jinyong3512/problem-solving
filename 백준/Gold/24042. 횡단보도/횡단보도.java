import java.io.*;
import java.util.*;

class Edge {
    int startVertex;
    int endVertex;
    int time;

    Edge(int startVertex, int endVertex, int time) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.time = time;
    }
}

class Vertex {
    int position;
    long time;

    Vertex(int position, long time) {
        this.position = position;
        this.time = time;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.get(v1).add(new Edge(v1, v2, i));
            graph.get(v2).add(new Edge(v2, v1, i));
        }

        /////////////////////////////////////////////////

        long[] distances = dijkstra(graph, M);
        System.out.println(distances[N]);

    }

    public static long[] dijkstra(ArrayList<ArrayList<Edge>> graph, int M) {

        PriorityQueue<Vertex> pQ = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.time < o2.time)
                    return -1;
                else if (o1.time == o2.time)
                    return 0;
                else
                    return 1;
            }
        });
        boolean[] visited = new boolean[graph.size()];
        long[] distances = new long[graph.size()];

        pQ.add(new Vertex(1, 0));
        for (int i = 0; i < distances.length; i++)
            distances[i] = Long.MAX_VALUE;


        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.position])
                continue;

            visited[curVertex.position] = true;
            distances[curVertex.position] = curVertex.time;

            for (int i = 0; i < graph.get(curVertex.position).size(); i++) {
                Edge curEdge = graph.get(curVertex.position).get(i);

                long waitTime = -1;
                if (curVertex.time % M > curEdge.time) {
                    waitTime = M - curVertex.time % M + curEdge.time;
                } else if (curVertex.time % M == curEdge.time)
                    waitTime = 0;
                else
                    waitTime = curEdge.time - curVertex.time % M;

                pQ.add(new Vertex(curEdge.endVertex, curVertex.time + waitTime + 1));


            }


        }

        return distances;


    }
}