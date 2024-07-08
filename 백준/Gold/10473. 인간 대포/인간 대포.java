import java.io.*;
import java.util.*;

class Data {
    double y, x;

    Data(double y, double x) {
        this.y = y;
        this.x = x;
    }
}

class Vertex {
    int number;
    double distance;

    Vertex(int number, double distance) {
        this.number = number;
        this.distance = distance;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        ArrayList<Data> graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        double y = Double.parseDouble(st.nextToken());
        double x = Double.parseDouble(st.nextToken());
        graph.add(new Data(y, x));

        st = new StringTokenizer(br.readLine());
        y = Double.parseDouble(st.nextToken());
        x = Double.parseDouble(st.nextToken());
        graph.add(new Data(y, x));

        int n = Integer.parseInt(br.readLine());
        for (int N = 0; N < n; N++) {
            st = new StringTokenizer(br.readLine());
            y = Double.parseDouble(st.nextToken());
            x = Double.parseDouble(st.nextToken());
            graph.add(new Data(y, x));
        }

        /////////////////////////////////////////////

        double[] distances = dijkstra(graph);

        System.out.printf("%.6f\n", distances[1]);
    }

    public static double[] dijkstra(ArrayList<Data> graph) {

        double[] distances = new double[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<Vertex> pQ = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.distance < o2.distance)
                    return -1;
                else if (o1.distance == o2.distance)
                    return 0;
                else
                    return 1;
            }
        });

        for (int i = 0; i < distances.length; i++)
            distances[i] = Double.MAX_VALUE;
        pQ.add(new Vertex(0, 0));

        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.number])
                continue;

            visited[curVertex.number] = true;
            distances[curVertex.number] = curVertex.distance;

            for (int i = 0; i < graph.size(); i++) {

                double curGap =
                        Math.sqrt(
                                Math.pow(graph.get(i).y - graph.get(curVertex.number).y, 2) +
                                        Math.pow(graph.get(i).x - graph.get(curVertex.number).x, 2)
                        );
                double distance = curGap / 5;
                if (curVertex.number == 0) {
                    distance = curGap / 5;
                } else {
                    distance = Math.min(curGap / 5, 2 + Math.abs(curGap - 50) / 5);
                }
                pQ.add(new Vertex(i, curVertex.distance + distance));

            }


        }

        return distances;

    }
}