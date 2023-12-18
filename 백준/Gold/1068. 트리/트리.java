import java.io.*;
import java.util.*;

public class Main {

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int rootValue = -1;
        int deleteNumber;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1)
                rootValue = i;
            else
                graph.get(parent).add(i);

        }

        deleteNumber = Integer.parseInt(br.readLine());

        ////////////////////////////////////////////////////////

        if (deleteNumber == rootValue)
            System.out.println("0");
        else {
            dfs(graph, rootValue, deleteNumber);
            dfs2(graph, rootValue);

            System.out.println(answer);
        }
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int cur, int deleteNumber) {
        for (int i = 0; i < graph.get(cur).size(); i++) {
            if (graph.get(cur).get(i) == deleteNumber) {
                graph.get(cur).remove(i);
                break;
            }
            dfs(graph, graph.get(cur).get(i), deleteNumber);
        }
    }

    public static void dfs2(ArrayList<ArrayList<Integer>> graph, int cur) {
        if (graph.get(cur).size() == 0) {
            answer++;
            return;
        }

        for (int i = 0; i < graph.get(cur).size(); i++) {
            dfs2(graph, graph.get(cur).get(i));
        }
    }
}