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
    ArrayList<Integer> course;

    Vertex(int number, int weight, ArrayList<Integer> course) {
        this.number = number;
        this.weight = weight;
        this.course = course;
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

        ArrayList<ArrayList<Integer>> courses = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            courses.add(new ArrayList<>());
        long[] distances = dijkstra(graph, A, courses);

        sb.append(distances[B]).append("\n");
        sb.append(courses.get(B).size()).append("\n");
        for(Integer value: courses.get(B))
            sb.append(value).append(" ");
        System.out.println(sb);

    }

    public static long[] dijkstra(ArrayList<ArrayList<Edge>> graph, int A, ArrayList<ArrayList<Integer>> courses) {

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

        pQ.add(new Vertex(A, 0, new ArrayList<>()));

        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.number])
                continue;

            visited[curVertex.number] = true;
            distances[curVertex.number] = curVertex.weight;
            for (Integer value : curVertex.course)
                courses.get(curVertex.number).add(value);
            courses.get(curVertex.number).add(curVertex.number);


            for (int i = 0; i < graph.get(curVertex.number).size(); i++) {

                Edge curEdge = graph.get(curVertex.number).get(i);

                ArrayList<Integer> course = new ArrayList<>();
                for (Integer value : curVertex.course) {
                    course.add(value);
                }
                course.add(curVertex.number);

                pQ.add(new Vertex(curEdge.endVertex, curVertex.weight + curEdge.weight, course));

            }

        }

        return distances;


    }
}