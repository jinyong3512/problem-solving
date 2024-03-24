import java.io.*;
import java.util.*;

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        int[][] map;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < map.length; i++) {
            String inputLine = br.readLine();

            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = inputLine.charAt(j) - '0';
            }
        }

        /////////////////////////////////////////////////////////////////

        // 8 되면 9로 넣고 점수 계산
        // 1 되면 2로 넣고 점수 계산
        // 되면 +1로 늘리고 정답처리 할꺼임
        for (int height = 8; height >= 1; height--) {
            boolean[][] visited = new boolean[N][M];

            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (!visited[i][j] && map[i][j] <= height) {
                        isSafe(map, i, j, height, visited);
                    }
                }
            }
        }

        System.out.println(answer);


    }

    public static void isSafe(int[][] map, int i, int j, int height, boolean[][] visited) {

        ArrayList<Point> log = new ArrayList<>();
        log.add(new Point(i, j));

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));

        visited[i][j] = true;

        boolean can = true;

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();
            log.add(tmp);

            for (int direction = 0; direction < 4; direction++) {
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (height >= map[newY][newX]) {
                    if (!visited[newY][newX]) {
                        visited[newY][newX] = true;

                        if (newY == 0 || newY == map.length - 1 || newX == 0 || newX == map[0].length - 1) {
                            can = false;
                        } else {
                            queue.add(new Point(newY, newX));
                        }

                    }
                }
            }
        }

        if (can) {
            for (Point point : log) {
                answer += (height + 1 - map[point.y][point.x]);
                map[point.y][point.x] = height + 1;
            }
        }
    }
}