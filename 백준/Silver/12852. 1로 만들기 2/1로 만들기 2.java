import java.io.*;
import java.util.*;

class Data {
    int value;
    int depth;
    ArrayList<Integer> arrayList = new ArrayList<>();

    Data(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;

        N = Integer.parseInt(br.readLine());
        ///////////////////////////////////////////////////////////////////

        int[] dp = new int[N + 1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int i = 1; i <= N; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[N] = 0;

        for (int i = N; i >= 2; i--) {
            if (i % 3 == 0) {
                graph.get(i).add(i / 3);
                dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
            }
            if (i % 2 == 0) {
                graph.get(i).add(i / 2);
                dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
            }
            graph.get(i).add(i - 1);
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }

        sb.append(dp[1]).append("\n");

        boolean[] visited = new boolean[N + 1];
        Queue<Data> queue = new LinkedList<>();

        visited[N] = true;
        Data data = new Data(N, 0);
        data.arrayList.add(N);
        queue.add(data);

        while (!queue.isEmpty()) {
            Data tmp = queue.remove();

            if (tmp.value == 1) {
                for (int i = 0; i < tmp.arrayList.size(); i++) {
                    sb.append(tmp.arrayList.get(i) + " ");
                }
                break;
            }

            for (int i = 0; i < graph.get(tmp.value).size(); i++) {
                if (!visited[graph.get(tmp.value).get(i)]) {
                    visited[graph.get(tmp.value).get(i)] = true;
                    Data newData = new Data(graph.get(tmp.value).get(i), tmp.depth + 1);
                    for (int v : tmp.arrayList) {
                        newData.arrayList.add(v);
                    }
                    newData.arrayList.add(graph.get(tmp.value).get(i));
                    queue.add(newData);
                }
            }

        }

        System.out.println(sb);


    }
}