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
    int prev;

    Vertex(int number, int weight, int prev) {
        this.number = number;
        this.weight = weight;
        this.prev = prev;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n;
        int m;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        int A, B;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(v1).add(new Edge(v1, v2, weight));
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////////////

        int[] prev = new int[n + 1];
        long[] distances = dijkstra(graph, A, prev);

        Deque<Integer> course = new ArrayDeque<>();

        int cur = B;
        while(true){
            course.addFirst(cur);
            cur = prev[cur];
            if(cur==0)
                break;
        }

        sb.append(distances[B]).append("\n");

        sb.append(course.size()).append("\n");
        while(!course.isEmpty())
            sb.append(course.removeFirst()).append(" ");

        System.out.println(sb);

    }

    public static long[] dijkstra(ArrayList<ArrayList<Edge>> graph, int A, int[] prevs) {

        long[] distances = new long[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<Vertex> pQ = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
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

        for (int i = 0; i < graph.size(); i++)
            distances[i] = Integer.MAX_VALUE;

        pQ.add(new Vertex(A, 0, 0));

        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.number])
                continue;

            visited[curVertex.number] = true;
            distances[curVertex.number] = curVertex.weight;
            prevs[curVertex.number] = curVertex.prev;


            for (int i = 0; i < graph.get(curVertex.number).size(); i++) {
                Edge curEdge = graph.get(curVertex.number).get(i);
                pQ.add(new Vertex(curEdge.endVertex, curVertex.weight + curEdge.weight, curVertex.number));

            }

        }

        return distances;


    }
}