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

        int t = 0;
        while(true){
            t++;

            int N;
            int[][] arr;

            N = Integer.parseInt(br.readLine());
            if(N==0)
                break;

            arr = new int[N][N];
            for(int i =0 ; i < N ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N ; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /////////////////////////////////////////////////////

            int[][] distances = dijkstra(arr, 0, 0);
            sb.append("Problem ").append(t).append(": ").append(distances[N-1][N-1]).append("\n");

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

        pQ.add(new Vertex(startY, startX, arr[startY][startX]));
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

                pQ.add(new Vertex(newY, newX, curVertex.weight + arr[newY][newX]));
            }

        }
        return distances;
    }
}