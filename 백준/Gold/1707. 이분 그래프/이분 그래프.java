import java.io.*;
import java.util.*;

class Data {
    int vertexNumber;
    int color;

    Data(int vertexNumber, int color) {
        this.vertexNumber = vertexNumber;
        this.color = color;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            int V, E;
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int vertex1 = Integer.parseInt(st.nextToken());
                int vertex2 = Integer.parseInt(st.nextToken());

                graph.get(vertex1).add(vertex2);
                graph.get(vertex2).add(vertex1);
            }

            ///////////////////////////////////////////////////

            int[] colors = new int[V + 1];

            boolean can = true;
            for (int v = 1; v <= V; v++) {

                if (colors[v] != 0)
                    continue;

                colors[v] = 1;
                Queue<Data> queue = new LinkedList<>();
                queue.add(new Data(v, 1));

                while (!queue.isEmpty()) {
                    Data curData = queue.remove();

                    int curVertexNumber = curData.vertexNumber;
                    int curColor = curData.color;

                    for (int i = 0; i < graph.get(curVertexNumber).size(); i++) {
                        int nextVertexNumber = graph.get(curVertexNumber).get(i);

                        if (colors[nextVertexNumber] == 0) {
                            colors[nextVertexNumber] = curColor == 1 ? 2 : 1;
                            queue.add(new Data(nextVertexNumber, colors[nextVertexNumber]));
                        } else if (colors[nextVertexNumber] == curColor) {
                            can = false;
                        }

                    }
                }
            }

            if (can) {
                sb.append("YES").append("\n");
            } else
                sb.append("NO").append("\n");

        }

        System.out.println(sb);

    }
}
