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

        int n, m, r;
        int[] arr;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            int a, b, l;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(a, b, l));
            graph.get(b).add(new Edge(b, a, l));
        }

        int answer = 0;

        // O(n+r)
//        for (int t = 1; t <= n; t++) {
//
//            int answerCandidate = 0;
//            boolean[] visited = new boolean[n + 1];
//            Queue<Vertex> queue = new LinkedList<>();
//
//            queue.add(new Vertex(t, 0));
//
//            while (!queue.isEmpty()) {
//                Vertex curVertex = queue.remove();
//
//                if (!visited[curVertex.number]) {
//                    visited[curVertex.number] = true;
//                    answerCandidate += arr[curVertex.number];
//                }
//
//                for (int i = 0; i < graph.get(curVertex.number).size(); i++) {
//                    Edge curEdge = graph.get(curVertex.number).get(i);
//
//                    if (curEdge.weight + curVertex.weight <= m) {
//                        queue.add(new Vertex(curEdge.endVertex, curEdge.weight + curVertex.weight));
//                    }
//                }
//            }
//            answer = Math.max(answer, answerCandidate);
//        }
        for (int t = 1; t <= n; t++) {

            int answerCandidate = 0;
            boolean[] visited = new boolean[n + 1];
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

            pQ.add(new Vertex(t, 0));

            while (!pQ.isEmpty()) {
                Vertex curVertex = pQ.remove();

                if (visited[curVertex.number])
                    continue;

                visited[curVertex.number] = true;
                answerCandidate += arr[curVertex.number];

                for (int i = 0; i < graph.get(curVertex.number).size(); i++) {
                    Edge curEdge = graph.get(curVertex.number).get(i);

                    if (curEdge.weight + curVertex.weight <= m) {
                        pQ.add(new Vertex(curEdge.endVertex, curEdge.weight + curVertex.weight));
                    }
                }
            }
            answer = Math.max(answer, answerCandidate);
        }


        System.out.println(answer);


    }


}