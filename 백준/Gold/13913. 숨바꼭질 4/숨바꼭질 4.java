import java.io.*;
import java.util.*;

class Data {
    int number;
    int depth;

    Data(int number, int depth) {
        this.number = number;
        this.depth = depth;
    }
}

public class Main {

    // N이 10만 10^6
    // X-1 or X+1 or 2*X

    // 어떻게 이동해야 하는지
    // 그러면 해당 number는 누가 와서 만든건지 알아야함 2는 1이 만들었다.
    // prev를 쓰자!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////////////

        int[] prev = new int[100001];

        boolean[] visited = new boolean[100001];
        Queue<Data> queue = new LinkedList<>();

        visited[N] = true;
        queue.add(new Data(N, 0));

        while (!queue.isEmpty()) {
            Data tmp = queue.remove();

            if (tmp.number == K) {
                sb.append(tmp.depth).append("\n");
                break;
            }

            // X-1
            if (tmp.number - 1 >= 0 && !visited[tmp.number - 1]) {
                visited[tmp.number - 1] = true;
                prev[tmp.number - 1] = tmp.number;
                queue.add(new Data(tmp.number - 1, tmp.depth + 1));
            }
            if (tmp.number + 1 <= 100000 && !visited[tmp.number + 1]) {
                visited[tmp.number + 1] = true;
                prev[tmp.number + 1] = tmp.number;
                queue.add(new Data(tmp.number + 1, tmp.depth + 1));
            }
            if (tmp.number * 2 <= 100000 && !visited[tmp.number * 2]) {
                visited[tmp.number * 2] = true;
                prev[tmp.number * 2] = tmp.number;
                queue.add(new Data(tmp.number * 2, tmp.depth + 1));
            }

        }

        recursion(sb, prev, N, K);

        System.out.println(sb);

    }

    public static void recursion(StringBuilder sb, int[] prev, int N, int cur) {
        // K를 넣어서 맨 뒤를 구하는거야

        if (cur == N) {

            sb.append(cur).append(" ");

            return;
        }

        recursion(sb, prev, N, prev[cur]);

        sb.append(cur).append(" ");
    }
}