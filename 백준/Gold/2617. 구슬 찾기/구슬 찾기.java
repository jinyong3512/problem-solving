import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 무게가 모두 다른 N개의 구슬
        // N은 홀수이다
        // 1 ~ N 으로 넘버링

        // 무게 중간인 구슬 찾기

        // M개의 쌍을 골라서 양팔 저울에 올림

        int N, M;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> graph2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            graph.get(second).add(first);
            graph2.get(first).add(second);
        }

        /////////////////////////////////////////////////////////////

        boolean[] answers = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            dfs(graph, i, visited);

            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (visited[j])
                    count++;
            }

            count--;

            if(count > N/2)
                answers[i] = true;

        }

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            dfs(graph2, i, visited);

            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (visited[j])
                    count++;
            }

            count--;

            if(count > N/2)
                answers[i] = true;

        }

        int answer = 0;
        for(int i = 1 ; i <= N ; i++){
            if(answers[i])
                answer++;
        }
        System.out.println(answer);



    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int curIndex, boolean[] visited) {
        visited[curIndex] = true;

        for (int i = 0; i < graph.get(curIndex).size(); i++) {
            if (!visited[graph.get(curIndex).get(i)])
                dfs(graph, graph.get(curIndex).get(i), visited);
        }
    }
}
