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

        int N, M;
        char[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = inputLine.charAt(j);
            }
        }

        ///////////////////////////////////////////////////////

        int answer = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'W')
                    continue;

                Queue<Point> queue = new LinkedList<>();
                queue.add(new Point(i, j, 0));

                boolean[][] visited = new boolean[N][M];
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    Point curPoint = queue.remove();

                    answer = Math.max(answer,curPoint.depth);

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = curPoint.y + dy[direction];
                        int newX = curPoint.x + dx[direction];

                        if (newY < 0 || newY >= N || newX < 0 || newX >= M)
                            continue;

                        if (arr[newY][newX] == 'W')
                            continue;

                        if (visited[newY][newX])
                            continue;

                        visited[newY][newX] = true;
                        queue.add(new Point(newY, newX, curPoint.depth + 1));
                    }

                }
            }
        }

        System.out.println(answer);

    }
}
