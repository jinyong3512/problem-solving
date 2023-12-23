import java.io.*;
import java.util.*;

class Point {
    int y, x;
    int depth;
    int lastChance;
    int day;

    Point(int y, int x, int depth, int lastChance, int day) {
        this.y = y;
        this.x = x;
        this.depth = depth;
        this.lastChance = lastChance;
        this.day = day;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, K;
        int[][] map;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < map.length; i++) {
            String line = br.readLine();
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        ///////////////////////////////////////////////////////////////////////

        int[][][] lastChances = new int[N][M][2];
        for (int i = 0; i < lastChances.length; i++) {
            for (int j = 0; j < lastChances[0].length; j++) {
                lastChances[i][j][0] = -1;
                lastChances[i][j][1] = -1;
            }
        }
        lastChances[0][0][1] = K;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0, K, 1));

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

            if (tmp.y == map.length - 1 && tmp.x == map[0].length - 1) {
                sb.append(tmp.depth + 1);
                break;
            }

            // 낮일 경우 벽을 부실 수 있다!
            if (tmp.day == 1) {

                // 제자리에서 하루 보내기
                if (lastChances[tmp.y][tmp.x][0] < tmp.lastChance) {
                    lastChances[tmp.y][tmp.x][0] = tmp.lastChance;
                    queue.add(new Point(tmp.y, tmp.x, tmp.depth + 1, tmp.lastChance, 0));
                }

                // 부실 수 있는 기회가 있다!!
                if (tmp.lastChance > 0) {
                    // 부실게
                    for (int direction = 0; direction < 4; direction++) {
                        int newY = tmp.y + dy[direction];
                        int newX = tmp.x + dx[direction];

                        if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                            continue;

                        if (map[newY][newX] == 0)
                            continue;

                        if (lastChances[newY][newX][0] < tmp.lastChance - 1) {
                            lastChances[newY][newX][0] = tmp.lastChance - 1;
                            queue.add(new Point(newY, newX, tmp.depth + 1, tmp.lastChance - 1, 0));
                        }
                    }
                }
                // 안 부실게
                for (int direction = 0; direction < 4; direction++) {
                    int newY = tmp.y + dy[direction];
                    int newX = tmp.x + dx[direction];

                    if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                        continue;

                    if (map[newY][newX] == 1)
                        continue;

                    if (lastChances[newY][newX][0] < tmp.lastChance) {
                        lastChances[newY][newX][0] = tmp.lastChance;
                        queue.add(new Point(newY, newX, tmp.depth + 1, tmp.lastChance, 0));
                    }
                }


            } else {
                // 제자리에서 하루 보내기
                if (lastChances[tmp.y][tmp.x][1] < tmp.lastChance) {
                    lastChances[tmp.y][tmp.x][1] = tmp.lastChance;
                    queue.add(new Point(tmp.y, tmp.x, tmp.depth + 1, tmp.lastChance, 1));
                }

                // 안 부실게
                for (int direction = 0; direction < 4; direction++) {
                    int newY = tmp.y + dy[direction];
                    int newX = tmp.x + dx[direction];

                    if (newY < 0 || newY >= map.length || newX < 0 || newX >= map[0].length)
                        continue;

                    if (map[newY][newX] == 1)
                        continue;

                    if (lastChances[newY][newX][1] < tmp.lastChance) {
                        lastChances[newY][newX][1] = tmp.lastChance;
                        queue.add(new Point(newY, newX, tmp.depth + 1, tmp.lastChance, 1));
                    }
                }

            }

        }

        if (sb.length() == 0) {
            sb.append("-1");
        }

        System.out.println(sb);


    }
}