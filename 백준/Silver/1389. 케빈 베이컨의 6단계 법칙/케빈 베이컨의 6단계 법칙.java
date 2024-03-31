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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        boolean[][] graph;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken())-1;
            int vertex2 = Integer.parseInt(st.nextToken())-1;
            graph[vertex1][vertex2] = true;
            graph[vertex2][vertex1] = true;
        }

        //////////////////////////////////////////////////////

        int[] bakens = new int[N];

        for (int i = 0; i < N; i++) {
            int baken = 0;

            boolean[] visited = new boolean[N];
            visited[i] = true;

            Queue<Data> queue = new LinkedList<>();
            queue.add(new Data(i, 0));

            while (!queue.isEmpty()) {
                Data curData = queue.remove();

                baken += curData.depth;

                for (int j = 0; j < N; j++) {
                    if (graph[curData.vertex][j] && !visited[j]) {
                        visited[j] = true;
                        queue.add(new Data(j, curData.depth + 1));
                    }
                }

            }

            bakens[i] = baken;
        }

        int findAnswerIndex = 0;

        for(int i = 0 ; i < bakens.length ; i++){
            if( bakens[findAnswerIndex] > bakens[i]){
                findAnswerIndex = i;
            }
        }

//        for(int baken : bakens)
//            System.out.println(baken);

        System.out.println(findAnswerIndex+1);

    }
}