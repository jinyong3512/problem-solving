import java.io.*;
import java.util.*;

class Point {
    int y, x, depth;

    Point(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 섬을 넘버링하고
        // 1,2,3,4 로 다 고침
        // 모든섬의 모든 점을 bfs 때려서 자신과 다른 number가 나오면 stop이지

        int N;
        int[][] map;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ///////////////////////////////////////////////////////////////////////

        int answer = Integer.MAX_VALUE;

        int islandNumber = 1;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    dfs(map, islandNumber, visited, i, j);
                    islandNumber++;
                }
            }
        }

//        for(int i = 0; i < map.length ; i++){
//            for(int j =0 ; j < map[0].length ; j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    answer = Math.min(answer,bfs(map, i, j));
                }
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int[][] map, int islandNumber, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        map[i][j] = islandNumber;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                continue;
            if (visited[newY][newX])
                continue;
            if (map[newY][newX] == 0)
                continue;

            dfs(map, islandNumber, visited, newY, newX);
        }

    }

    public static int bfs(int[][] map, int i, int j) {
        int islandNumber = map[i][j];

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        queue.add(new Point(i, j, 0));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

//            System.out.println("tmp.x = " + tmp.x + " tmp.y =  "+ tmp.y + " tmp.depth = " + tmp.depth);

            if (tmp.depth !=0 && map[tmp.y][tmp.x] != 0 && map[tmp.y][tmp.x] != islandNumber) {
                return tmp.depth-1;
            }

            for (int direction = 0; direction < 4; direction++) {
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                    continue;

                if(visited[newY][newX])
                    continue;

                if (map[newY][newX] == islandNumber)
                    continue;

                visited[newY][newX] = true;
                queue.add(new Point(newY, newX, tmp.depth + 1));
            }

        }

        return Integer.MAX_VALUE;
    }
}