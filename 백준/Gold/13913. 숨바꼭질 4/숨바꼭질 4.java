import java.io.*;
import java.util.*;

class Data {
    int position;
    int depth;

    Data(int position, int depth) {
        this.position = position;
        this.depth = depth;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        /////////////////////////////////////////

        Queue<Data> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int[] prev = new int[100001];

        queue.add(new Data(N, 0));
        visited[N] = true;

        while (!queue.isEmpty()) {
            Data curData = queue.remove();

            if (curData.position == K) {
                sb.append(curData.depth).append("\n");
                break;
            }

            for (int direction = 0; direction < 3; direction++) {

                int newPosition;

                if (direction == 0)
                    newPosition = curData.position - 1;
                else if (direction == 1)
                    newPosition = curData.position + 1;
                else
                    newPosition = curData.position * 2;

                if (newPosition < 0 || newPosition > 100000)
                    continue;

                if (visited[newPosition])
                    continue;

                visited[newPosition] = true;
                prev[newPosition] = curData.position;
                queue.add(new Data(newPosition, curData.depth + 1));
            }
        }
        recursion(K, N, prev, sb);
        sb.append(K).append(" ");
        System.out.println(sb);
    }

    public static void recursion(int curPosition, int N, int[] prev, StringBuilder sb) {
        if(curPosition == N)
            return;

        recursion(prev[curPosition], N, prev, sb);
        sb.append(prev[curPosition]).append(" ");
    }
}