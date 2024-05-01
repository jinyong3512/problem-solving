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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 18:10 시작
        // N 2~100

        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Point>> bigArrayList = new ArrayList<>();
        for (int i = 0; i < N * N; i++) {
            bigArrayList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y, x, b, a;
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            a = Integer.parseInt(st.nextToken()) - 1;

            bigArrayList.get(y * N + x).add(new Point(b, a));
        }


        int answer = 1;

        boolean[][] actioned = new boolean[N][N];
        boolean[][] turnOn = new boolean[N][N];
        Queue<Point> willActionPoint = new LinkedList<>();

        turnOn[0][0] = true;
        willActionPoint.add(new Point(0, 0));

        while (true) {

            // 새로운 지점에서 불 키자!
            while (!willActionPoint.isEmpty()) {
                Point curPoint = willActionPoint.remove();
                for (Point curPoint2 : bigArrayList.get(curPoint.y * N + curPoint.x)) {
                    if (!turnOn[curPoint2.y][curPoint2.x]) {
                        answer++;
                        turnOn[curPoint2.y][curPoint2.x] = true;
                    }
                }
                actioned[curPoint.y][curPoint.x] = true;
            }

            // 불 킨곳 갈 수 있을까?
            boolean[][] tmpVisited = new boolean[N][N];
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(0, 0));

            while (!queue.isEmpty()) {
                Point curPoint = queue.remove();

                for (int direction = 0; direction < 4; direction++) {
                    int newY = curPoint.y + dy[direction];
                    int newX = curPoint.x + dx[direction];

                    if (newY < 0 || newY >= N || newX < 0 || newX >= N)
                        continue;

                    if (tmpVisited[newY][newX])
                        continue;

                    if (turnOn[newY][newX]) {

                        if (actioned[newY][newX]) {
                            tmpVisited[newY][newX] = true;
                            queue.add(new Point(newY, newX));
                        } else {
                            tmpVisited[newY][newX] = true;
                            willActionPoint.add(new Point(newY, newX));
                        }
                    }
                }
            }

            if (willActionPoint.isEmpty()) {
                break;
            }
        }

        System.out.println(answer);

    }
}
