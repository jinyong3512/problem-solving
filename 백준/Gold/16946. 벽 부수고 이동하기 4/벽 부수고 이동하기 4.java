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

    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        boolean[][] wall;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wall = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < M; j++) {
                if (inputLine.charAt(j) == '1') {
                    wall[i][j] = true;
                }
            }
        }

        ////////////////////////////////////////////

        int[][] answer = new int[N][M];

        boolean[][] visited = new boolean[N][M];

        Queue<Point> queue = new LinkedList<>();
//        Queue<Point> walls = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!wall[i][j] && !visited[i][j]) {
                    count = 0;
                    queue.add(new Point(i, j));
                    visited[i][j] = true;

                    HashMap<Integer, Boolean> walls = new HashMap<>();

                    while (!queue.isEmpty()) {
                        count++;

                        Point tmp = queue.remove();
//                        System.out.println("tmp.y = " + tmp.y + " tmp.x = "+ tmp.x);

                        for (int direction = 0; direction < 4; direction++) {

                            int newY = tmp.y + dy[direction];
                            int newX = tmp.x + dx[direction];

                            if (newY < 0 || newX < 0 || newY >= wall.length || newX >= wall[0].length)
                                continue;

                            if (wall[newY][newX]) {
//                                if (!visited2[newY][newX]) {
//                                    visited2[newY][newX] = true;
//                                    walls.add(new Point(newY, newX));
//                                }
                                walls.put(newY * wall[0].length + newX, true);
                            } else {
                                if (!visited[newY][newX]) {
                                    visited[newY][newX] = true;
                                    queue.add(new Point(newY, newX));
                                }
                            }

                        }

                    }
//                    System.out.println(count);

//                    while (!walls.isEmpty()) {
//                        Point tmp = walls.remove();
//
//                        answer[tmp.y][tmp.x] += count;
//
//                    }

                    for (Integer key : walls.keySet()) {
                        answer[key / wall[0].length][key % wall[0].length] += count;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!wall[i][j])
                    sb.append("0");
                else
                    sb.append((answer[i][j] + 1) % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}