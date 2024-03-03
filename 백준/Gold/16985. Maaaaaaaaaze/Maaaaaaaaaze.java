import java.io.*;
import java.util.*;

class Point {
    int z, y, x, depth;

    Point(int z, int y, int x, int depth) {
        this.z = z;
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Main {

    public static int answer = Integer.MAX_VALUE;

    public static int[] dz = new int[]{-1, 1, 0, 0, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 15:55
        // 0은 못감 1은 감(=하얀색)

        boolean[][][] map = new boolean[5][5][5];

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) {
                    if (st.nextToken().equals("1")) {
                        map[k][i][j] = true;
                    }
                }
            }
        }

        ////////////////////////////////////////////////

        // 각 판을 회전시키는 경우의 수
        // 다시 랜덤으로 5개 P
        // 통과 해보기

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[5];
        permutation(map, visited, deque);

        if (answer == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(answer);
    }

    public static void rotate(boolean[][] board) {
        boolean[][] newBoard = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newBoard[i][j] = board[board.length - 1 - j][i];
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }

    public static void permutation(boolean[][][] map, boolean[] visited, Deque<Integer> deque) {
        if (deque.size() == 5) {

            boolean[][][] newMap = new boolean[5][5][5];
            int k = 0;
            for (Integer number : deque) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        newMap[k][i][j] = map[number][i][j];
                    }
                }
                k++;
            }
            recursion(newMap, 0);

            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!visited[i]) {
                deque.add(i);
                visited[i] = true;
                permutation(map, visited, deque);
                visited[i] = false;
                deque.removeLast();
            }
        }
    }

    public static void recursion(boolean[][][] map, int index) {

        if (index == 5) {

            bfs(map, 0, 0, 0, 4, 4, 4);
            bfs(map, 0, 4, 0, 4, 0, 4);
            bfs(map, 0, 0, 4, 4, 4, 0);
            bfs(map, 0, 4, 4, 4, 0, 0);

            return;
        }

        for (int i = 0; i < 4; i++) {
            recursion(map, index + 1);
            rotate(map[index]);
        }

    }

    public static void bfs(boolean[][][] map, int startZ, int startY, int startX, int endZ, int endY, int endX) {

        if (!map[startZ][startY][startX])
            return;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startZ, startY, startX, 0));

        boolean[][][] visited = new boolean[5][5][5];
        visited[startZ][startY][startX] = true;

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

            if (tmp.z == endZ && tmp.y == endY && tmp.x == endX) {
                answer = Math.min(answer, tmp.depth);
                break;
            }

            for (int direction = 0; direction < 6; direction++) {
                int newZ = tmp.z + dz[direction];
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (newZ < 0 || newY < 0 || newX < 0 || newZ >= 5 || newY >= 5 || newX >= 5)
                    continue;

                if (visited[newZ][newY][newX])
                    continue;

                if (!map[newZ][newY][newX])
                    continue;

                visited[newZ][newY][newX] = true;
                queue.add(new Point(newZ, newY, newX, tmp.depth + 1));

            }


        }


    }
}