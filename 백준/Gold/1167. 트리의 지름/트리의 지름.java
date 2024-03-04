import java.io.*;
import java.util.*;

class Data {
    int vertex;
    int depth;

    Data(int vertex, int depth) {
        this.vertex = vertex;
        this.depth = depth;
    }
}

public class Main {

    public static int farDepth;
    public static int farIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // V 10만 10^5
        // V^2 이면 터진다

        // 트리의 지름이란 임의의 두 점 사이의 거리 중 가장 긴 것을 말함

        int V;
        ArrayList<ArrayList<Data>> graph = new ArrayList<>();

        V = Integer.parseInt(br.readLine());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int vertexNumber = Integer.parseInt(st.nextToken());
            while (true) {
                int number = Integer.parseInt(st.nextToken());

                if (number == -1)
                    break;

                int distance = Integer.parseInt(st.nextToken());

                graph.get(vertexNumber).add(new Data(number, distance));

            }
        }

        ////////////////////////////////////////////////////////////////

        boolean[] visited = new boolean[V + 1];
        visited[1] = true;
        dfs(graph, 1, 0, visited);

        farDepth = 0;
        visited = new boolean[V + 1];
        visited[farIndex] = true;
        dfs(graph, farIndex, 0, visited);

        System.out.println(farDepth);
    }

    public static void dfs(ArrayList<ArrayList<Data>> graph, int curVertexNumber, int depth, boolean[] visited) {

        if (farDepth < depth) {
            farDepth = depth;
            farIndex = curVertexNumber;
        }

        for (int i = 0; i < graph.get(curVertexNumber).size(); i++) {
            int nextVertexNumber = graph.get(curVertexNumber).get(i).vertex;
            int nextVertexDepth = graph.get(curVertexNumber).get(i).depth;

            if (!visited[nextVertexNumber]) {
                visited[nextVertexNumber] = true;
                dfs(graph, nextVertexNumber, depth + nextVertexDepth, visited);
            }

        }
    }
}