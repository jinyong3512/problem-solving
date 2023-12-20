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

        // 1400 시작
        int R, C;
        int[][] map;
        Point start = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < map.length; i++) {
            String line = br.readLine();
            for (int j = 0; j < map[0].length; j++) {
                char one = line.charAt(j);
                if (one == '#') {
                    map[i][j] = -1;
                } else if (one == 'F') {
                    map[i][j] = 0;
                } else if (one == 'J') {
                    map[i][j] = Integer.MAX_VALUE;
                    start = new Point(i, j, 0);
                } else if (one == '.') {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        /////////////////////////////////////////////////////////////////////////

        spreadFire(map);

//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[0].length; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Point> queue = new LinkedList<>();

        visited[start.y][start.x] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

//            System.out.println("y = " + tmp.y + " x = "+tmp.x + " depth = " + tmp.depth);

            if (tmp.y == 0 || tmp.y == map.length - 1 || tmp.x == 0 || tmp.x == map[0].length - 1) {
                sb.append(tmp.depth + 1);
                break;
            }

            for (int direction = 0; direction < 4; direction++) {
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                    continue;

                if (map[newY][newX] == -1)
                    continue;

                if (visited[newY][newX])
                    continue;

                // map의 값은 불이 있기 시작한 시간
                if (map[newY][newX] <= tmp.depth + 1)
                    continue;

                visited[newY][newX] = true;
                queue.add(new Point(newY, newX, tmp.depth + 1));

            }
        }

        if (sb.length() == 0) {
            sb.append("IMPOSSIBLE");
        }

        System.out.println(sb);

    }

    public static void spreadFire(int[][] map) {

        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    visited[i][j] = true;
                    queue.add(new Point(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

            map[tmp.y][tmp.x] = tmp.depth;

            for (int direction = 0; direction < 4; direction++) {
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                    continue;

                if (map[newY][newX] == -1)
                    continue;

                if (visited[newY][newX])
                    continue;

                visited[newY][newX] = true;
                queue.add(new Point(newY, newX, tmp.depth + 1));

            }
        }

    }
}