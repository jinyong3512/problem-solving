import java.io.*;
import java.util.*;

class Room {
    int y, x, price;

    Room(int y, int x, int price) {
        this.y = y;
        this.x = x;
        this.price = price;
    }
}

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

        // 월세와 편의점까지의 거리뿐
        // 지도 위의 모든 방에 편세권 점수를 매겨 그 중 편세권 점수가 가장 낮은 집 고르기
        // 편세권 점수 = (방에서 가장 가까운 편의점까지의 거리 X 월세)

        int N, M, R, C;
        ArrayList<Room> rooms = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int p = Integer.parseInt(st.nextToken());

            rooms.add(new Room(a, b, p));
        }

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            queue.add(new Point(c, d, 0));
        }

        //////////////////////////////////////////////////////////////

        int[][] distances = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();

            for (int direction = 0; direction < 4; direction++) {
                int newY = curPoint.y + dy[direction];
                int newX = curPoint.x + dx[direction];

                if (newY < 0 || newY >= N || newX < 0 || newX >= M)
                    continue;
                if (distances[newY][newX] != Integer.MAX_VALUE)
                    continue;

                distances[newY][newX] = curPoint.depth+1;
                queue.add(new Point(newY,newX,curPoint.depth+1));
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int i =0 ; i < R; i ++){
            Room curRoom = rooms.get(i);
            answer = Math.min(answer, distances[curRoom.y][curRoom.x] * curRoom.price);
        }
        System.out.println(answer);


    }
}