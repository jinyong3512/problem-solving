import java.io.*;
import java.util.*;

class Vertex {
    int y, x;
    int count;

    Vertex(int y, int x, int count) {
        this.y = y;
        this.x = x;
        this.count = count;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int M, N;
        boolean[][] map;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < M; j++) {
                if (inputLine.charAt(j) == '1')
                    map[i][j] = true;
            }
        }

        ////////////////////////////////////////

        dijkstra(map);

    }

    public static void dijkstra(boolean[][] map) {

        boolean[][] visited = new boolean[map.length][map[0].length];
        PriorityQueue<Vertex> pQ = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.count < o2.count)
                    return -1;
                else if (o1.count == o2.count)
                    return 0;
                else
                    return 1;
            }
        });

        pQ.add(new Vertex(0, 0, 0));

        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.y][curVertex.x])
                continue;

            visited[curVertex.y][curVertex.x] = true;

            if (curVertex.y == map.length - 1 && curVertex.x == map[0].length - 1) {
                System.out.println(curVertex.count);
                break;
            }

            for (int direction = 0; direction < 4; direction++) {
                int newY = curVertex.y + dy[direction];
                int newX = curVertex.x + dx[direction];

                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                    continue;

                if (map[newY][newX])
                    pQ.add(new Vertex(newY, newX, curVertex.count + 1));
                else
                    pQ.add(new Vertex(newY, newX, curVertex.count));

            }

        }


    }
}