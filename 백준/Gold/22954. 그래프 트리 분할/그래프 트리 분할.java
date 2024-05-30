import java.io.*;
import java.util.*;

class Edge {
    int startVertex;
    int endVertex;
    int index;

    Edge(int startVertex, int endVertex, int index) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.index = index;
    }
}

public class Main {

    public static int lastFindNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 정점 N, 간선 M개인 그래프!
        // 정점은 1~N, 간선은 1~M 로 번호가 있다
        // N: 10^5, M: 2*10^5

        // 간선을 삭제해서 서로 다른 크기의 트리 2개로 분할하자
        // 하나 이상의 정점을 가지고 있어야 하며, 동일한 정점이나 간선을 공유해선 안된다

        int N, M;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(u, v, i));
            graph.get(v).add(new Edge(v, u, i));
        }

        /////////////////////////////////////////////////////////////////////////

        if (N == 1 || N == 2) {
            System.out.println("-1");
            System.exit(0);
        }

        int[] vertexGroupNumber = new int[N + 1];
        int groupNumber = 0;

        for (int i = 1; i <= N; i++) {
            if (vertexGroupNumber[i] != 0)
                continue;

            groupNumber++;

            vertexGroupNumber[i] = groupNumber;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            while (!queue.isEmpty()) {
                int curVertexNumber = queue.remove();

                for (int j = 0; j < graph.get(curVertexNumber).size(); j++) {
                    Edge curEdge = graph.get(curVertexNumber).get(j);

                    if (vertexGroupNumber[curEdge.endVertex] != 0)
                        continue;

                    vertexGroupNumber[curEdge.endVertex] = groupNumber;

                    queue.add(curEdge.endVertex);
                }
            }
        }

        if (groupNumber == 1) {
            // 갯수가 다르게 직접 나눠서 출력

            // 방문 제일 마지막 노드 하나 왕따 시키기

            boolean[] visited = new boolean[N + 1];
            lastFindNode = 0;
            findNodeDFS(graph, visited, 1);

            vertexGroupNumber[lastFindNode] = 2;

            printAnswer(graph, vertexGroupNumber);

        } else if (groupNumber == 2) {
            // 이미 나누어져 있는데 갯수가 같으면 -1
            int N1 = 0;
            int N2 = 0;

            for (int i = 1; i <= N; i++) {
                if (vertexGroupNumber[i] == 1) N1++;
                else if (vertexGroupNumber[i] == 2) N2++;
            }

            if (N1 == N2) {
                System.out.println("-1");
            } else {

                printAnswer(graph, vertexGroupNumber);
            }

        } else if (groupNumber > 2) {
            System.out.println("-1");
        }
    }

    public static void findNodeDFS(ArrayList<ArrayList<Edge>> graph, boolean[] visited, int curVertexNumber) {

        visited[curVertexNumber] = true;
        lastFindNode = curVertexNumber;

        for (int i = 0; i < graph.get(curVertexNumber).size(); i++) {
            Edge curEdge = graph.get(curVertexNumber).get(i);

            if (!visited[curEdge.endVertex])
                findNodeDFS(graph, visited, curEdge.endVertex);
        }
    }

    public static void printAnswer(ArrayList<ArrayList<Edge>> graph, int[] vertexGroupNumber) {
        StringBuilder sb = new StringBuilder();

        int N1 = 0;
        int N2 = 0;

        for (int i = 1; i < vertexGroupNumber.length; i++) {
            if (vertexGroupNumber[i] == 1) N1++;
            if (vertexGroupNumber[i] == 2) N2++;
        }

        sb.append(N1).append(" ").append(N2).append("\n");

        for (int i = 1; i <= vertexGroupNumber.length; i++) {
            if (vertexGroupNumber[i] == 1) {
                boolean[] visited = new boolean[vertexGroupNumber.length];
                ArrayList<Integer> vertexesNumber = new ArrayList<>();
                ArrayList<Integer> edgesNumber = new ArrayList<>();
                findNodeDFS(graph, vertexGroupNumber, visited, vertexesNumber, edgesNumber, i, vertexGroupNumber[i]);


                for(int j =0 ; j < vertexesNumber.size() ; j++)
                    sb.append(vertexesNumber.get(j)).append(" ");
                sb.append("\n");

                for(int j =0 ; j < edgesNumber.size() ; j++)
                    sb.append(edgesNumber.get(j)).append(" ");
                sb.append("\n");

                break;
            }
        }

        for (int i = 1; i <= vertexGroupNumber.length; i++) {
            if (vertexGroupNumber[i] == 2) {
                boolean[] visited = new boolean[vertexGroupNumber.length];
                ArrayList<Integer> vertexesNumber = new ArrayList<>();
                ArrayList<Integer> edgesNumber = new ArrayList<>();
                findNodeDFS(graph, vertexGroupNumber, visited, vertexesNumber, edgesNumber, i, vertexGroupNumber[i]);


                for(int j =0 ; j < vertexesNumber.size() ; j++)
                    sb.append(vertexesNumber.get(j)).append(" ");
                sb.append("\n");

                for(int j =0 ; j < edgesNumber.size() ; j++)
                    sb.append(edgesNumber.get(j)).append(" ");
                sb.append("\n");

                break;
            }
        }

        System.out.println(sb);

    }

    public static void findNodeDFS(
            ArrayList<ArrayList<Edge>> graph,
            int[] vertexGroupNumber,
            boolean[] visited,
            ArrayList<Integer> vertexesNumber,
            ArrayList<Integer> edgesNumber,
            int curVertexNumber,
            int groupNumber) {

        visited[curVertexNumber] = true;
        vertexesNumber.add(curVertexNumber);

        for (int i = 0; i < graph.get(curVertexNumber).size(); i++) {
            Edge curEdge = graph.get(curVertexNumber).get(i);

            if (vertexGroupNumber[curEdge.endVertex] != groupNumber)
                continue;

            if (visited[curEdge.endVertex])
                continue;

            edgesNumber.add(curEdge.index);
            findNodeDFS(graph, vertexGroupNumber, visited, vertexesNumber, edgesNumber, curEdge.endVertex, groupNumber);
        }

    }
}