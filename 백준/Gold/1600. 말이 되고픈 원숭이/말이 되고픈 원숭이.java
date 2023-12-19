import java.io.*;
import java.util.*;

class Point {
    int y, x, lastChance, depth;

    Point(int y, int x, int lastChance, int depth) {
        this.y = y;
        this.x = x;
        this.lastChance = lastChance;
        this.depth = depth;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static int[] dy2 = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] dx2 = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int K;
        int W, H;
        int[][] arr;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////////////////////////

        // 같은 지점에 lastChance가 더 큰 애가 오면 queue에 넣는 작전을 쓰는거야 몸이 기억하고 있음

        int[][] maxLastChance = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                maxLastChance[i][j] = -1;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        maxLastChance[0][0] = K;
        queue.add(new Point(0, 0, K, 0));

        while (!queue.isEmpty()) {
            Point point = queue.remove();

            if (point.y == H - 1 && point.x == W - 1) {
                sb.append(point.depth);
                break;
            }

            // 점프 가능! 그래서 점프 하기
            if (point.lastChance != 0) {
                for (int direction = 0; direction < 8; direction++) {
                    int newY = point.y + dy2[direction];
                    int newX = point.x + dx2[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        continue;

                    if (arr[newY][newX] == 1)
                        continue;

                    if (maxLastChance[newY][newX] < point.lastChance - 1) {
                        maxLastChance[newY][newX] = point.lastChance - 1;
                        queue.add(new Point(newY, newX, point.lastChance - 1, point.depth + 1));
                    }
                }
            }

            // 점프 안할래
            for (int direction = 0; direction < 4; direction++) {
                int newY = point.y + dy[direction];
                int newX = point.x + dx[direction];

                if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                    continue;

                if (arr[newY][newX] == 1)
                    continue;

                if (maxLastChance[newY][newX] < point.lastChance) {
                    maxLastChance[newY][newX] = point.lastChance;
                    queue.add(new Point(newY, newX, point.lastChance, point.depth + 1));
                }
            }


        }

        if (sb.length() == 0)
            sb.append("-1");

        System.out.println(sb);

    }
}