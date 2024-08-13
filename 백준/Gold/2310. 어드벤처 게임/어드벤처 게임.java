import java.io.*;
import java.util.*;

class Vertex {
    String room;
    int fee;
    ArrayList<Integer> edges;

    Vertex(String room, int fee, ArrayList<Integer> edges) {
        this.room = room;
        this.fee = fee;
        this.edges = edges;
    }
}

public class Main {

    public static boolean can;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {

            int n = Integer.parseInt(br.readLine());

            if (n == 0)
                break;

            ArrayList<Vertex> graph = new ArrayList<>();
            graph.add(new Vertex(null, 0, null));

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                String room = st.nextToken();
                int fee = Integer.parseInt(st.nextToken());
                ArrayList<Integer> edges = new ArrayList<>();

                while (true) {
                    int edge = Integer.parseInt(st.nextToken());
                    if (edge == 0)
                        break;
                    edges.add(edge);
                }

                graph.add(new Vertex(room, fee, edges));
            }

            can = false;

            boolean[] visited = new boolean[n + 1];
            visited[1] = true;
            dfs(n, graph, visited, 0, 1);

            if(can)
                sb.append("Yes").append("\n");
            else
                sb.append("No").append("\n");

        }

        System.out.println(sb);
    }

    public static void dfs(int n, ArrayList<Vertex> graph, boolean[] visited, int curFee, int curVertex) {

        if (graph.get(curVertex).room.equals("E")) {

        } else if (graph.get(curVertex).room.equals("L")) {
            curFee = Math.max(curFee, graph.get(curVertex).fee);
        } else {
            curFee -= graph.get(curVertex).fee;
            if (curFee < 0)
                return;
        }

        if (curVertex == n) {
            can = true;
            return;
        }

        for (int i = 0; i < graph.get(curVertex).edges.size(); i++) {
            if (!visited[graph.get(curVertex).edges.get(i)]) {

                visited[graph.get(curVertex).edges.get(i)] = true;
                dfs(n, graph, visited, curFee, graph.get(curVertex).edges.get(i));
                visited[graph.get(curVertex).edges.get(i)] = false;
            }
        }


    }


}
