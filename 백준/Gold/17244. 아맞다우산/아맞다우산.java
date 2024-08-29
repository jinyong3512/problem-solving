import java.io.*;
import java.util.*;

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class DepthPoint {
    int y, x, depth;

    DepthPoint(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        ////////////////////////////////////////

        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i].charAt(j) == 'S')
                    points.add(new Point(i, j));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i].charAt(j) == 'X')
                    points.add(new Point(i, j));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i].charAt(j) == 'E')
                    points.add(new Point(i, j));
            }
        }

        int[][] distances = new int[points.size()][points.size()];
        for (int i = 0; i < distances.length; i++) {
            for (int j = i + 1; j < distances.length; j++) {
                int distance = bfs(N, M, arr, points.get(i), points.get(j));
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }

//        for(int i =0 ; i < distances.length ; i++){
//            for(int j =0 ; j < distances.length ; j++){
//                System.out.print(distances[i][j]+" ");
//            }
//            System.out.println();
//        }

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[distances.length];
        recursion(distances, deque, visited);
        System.out.println(answer);

    }

    public static int bfs(int N, int M, String[] arr, Point start, Point end) {

        Queue<DepthPoint> queue = new LinkedList<>();
        queue.add(new DepthPoint(start.y, start.x, 0));

        boolean[][] visited = new boolean[N][M];
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            DepthPoint curDepthPoint = queue.remove();

            if (curDepthPoint.y == end.y && curDepthPoint.x == end.x)
                return curDepthPoint.depth;

            for (int direction = 0; direction < 4; direction++) {
                int newY = curDepthPoint.y + dy[direction];
                int newX = curDepthPoint.x + dx[direction];

                if (newY < 0 || newY >= N || newX < 0 || newX >= M)
                    continue;

                if (arr[newY].charAt(newX) == '#')
                    continue;

                if (visited[newY][newX])
                    continue;

                visited[newY][newX] = true;
                queue.add(new DepthPoint(newY, newX, curDepthPoint.depth + 1));
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void recursion(int[][] distances, Deque<Integer> deque, boolean[] visited) {

        if (deque.size() == distances.length - 2) {

            int tmpAnswer = 0;


            int prev = 0;
            for (int index : deque) {
                tmpAnswer += distances[prev][index];
                prev = index;
            }
            tmpAnswer += distances[prev][distances.length - 1];

            answer = Math.min(answer, tmpAnswer);

            return;
        }

        for (int i = 1; i < distances.length - 1; i++) {
            if (visited[i])
                continue;
            deque.addLast(i);
            visited[i] = true;
            recursion(distances, deque, visited);
            visited[i] = false;
            deque.removeLast();
        }
    }
}