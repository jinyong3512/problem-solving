import java.util.*;
import java.io.*;

class Vertex {

    int y, x, weight;

    Vertex(int y, int x, int weight) {
        this.y = y;
        this.x = x;
        this.weight = weight;
    }

}


public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 원래 갈 수 없는 땅인 위치는 0 출력
        // 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1 출력

        int n, m;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //////////////////////////////////////////////////////

        int startY = -1;
        int startX = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }

        int[][] distances = dijkstra(arr, startY, startX);

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                if (distances[i][j] == Integer.MAX_VALUE) {
                    if (arr[i][j] == 0)
                        sb.append(0).append(" ");
                    else
                        sb.append(-1).append(" ");
                } else {
                    sb.append(distances[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static int[][] dijkstra(int[][] arr, int startY, int startX) {

        PriorityQueue<Vertex> pQ = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.weight - o2.weight;
            }
        });

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int[][] distances = new int[arr.length][arr[0].length];

        pQ.add(new Vertex(startY, startX, 0));
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        while (!pQ.isEmpty()) {
            Vertex curVertex = pQ.remove();

            if (visited[curVertex.y][curVertex.x])
                continue;

            visited[curVertex.y][curVertex.x] = true;
            distances[curVertex.y][curVertex.x] = curVertex.weight;

            for (int direction = 0; direction < 4; direction++) {
                int newY = curVertex.y + dy[direction];
                int newX = curVertex.x + dx[direction];

                if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length) {
                    continue;
                }

                if (arr[newY][newX] == 0)
                    continue;
                
                if(visited[newY][newX])
                    continue;

                pQ.add(new Vertex(newY, newX, curVertex.weight + 1));
            }

        }
        return distances;
    }
}