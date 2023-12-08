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

    //  (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)

    public static int[] dy = new int[]{-2, -2, 0, 0, 2, 2};
    public static int[] dx = new int[]{-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int r1, c1;
        int r2, c2;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////////////////

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new Point(r1, c1, 0));
        visited[r1][c1] = true;

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

            if (tmp.y == r2 && tmp.x == c2) {
                answer = tmp.depth;
                break;
            }

            for (int direction = 0; direction < 6; direction++) {
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (newY >= 0 && newY < N && newX >= 0 && newX < N) {
                    if (!visited[newY][newX]) {
                        visited[newY][newX] = true;
                        queue.add(new Point(newY, newX, tmp.depth + 1));
                    }
                }
            }

        }

        if(answer == Integer.MAX_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(answer);
        }


    }
}