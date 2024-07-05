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
        int[] visited = new int[100001];
        int[] count = new int[100001];

        queue.add(new Data(N, 0));
        for (int i = 0; i < visited.length; i++)
            visited[i] = Integer.MAX_VALUE;
        visited[N] = 0;
        count[N] = 1;

        while (!queue.isEmpty()) {
            Data curData = queue.remove();

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

                if (curData.depth + 1 < visited[newPosition]) {
                    visited[newPosition] = curData.depth + 1;
                    count[newPosition] += count[curData.position];
                    queue.add(new Data(newPosition, curData.depth + 1));
                } else if (curData.depth + 1 == visited[newPosition]) {
                    count[newPosition] += count[curData.position];
                } else {
                    continue;
                }
            }
        }

        System.out.println(visited[K]);
        System.out.println(count[K]);
    }
}