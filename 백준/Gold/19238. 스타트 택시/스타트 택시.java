import java.io.*;
import java.util.*;

// 03:15 도전

class Data {
    boolean wall;
    boolean start;
    int startNumber;
    ArrayList<Integer> endNumbers = new ArrayList<>();
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

    public static int[] dy = new int[]{-1, 0, 0, 1};
    public static int[] dx = new int[]{0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, energy;
        Data[][] board;
        int curY, curX;

        /////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        energy = Integer.parseInt(st.nextToken());
        board = new Data[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) {
                    board[i][j] = new Data();
                    board[i][j].wall = true;
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        curY = Integer.parseInt(st.nextToken()) - 1;
        curX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;

            if (board[startY][startX] == null)
                board[startY][startX] = new Data();
            board[startY][startX].start = true;
            board[startY][startX].startNumber = i;

            if (board[endY][endX] == null)
                board[endY][endX] = new Data();
            board[endY][endX].endNumbers.add(i);
        }

        ////////////////////////////////////////////////////////////////////////

        while (true) {

            // 손님이 있어?
            boolean existPerson = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != null && board[i][j].start)
                        existPerson = true;
                }
            }
            if (!existPerson) {
                break;
            }


            // 어떤 손님이 제일 가깝지!?
            Queue<Point> queue = new LinkedList<>();
            boolean[][] visited = new boolean[board.length][board[0].length];

            queue.add(new Point(curY, curX, 0));
            visited[curY][curX] = true;

            Point findStartPoint = null;
            while (!queue.isEmpty()) {
                Point point = queue.remove();

                if (board[point.y][point.x] != null && board[point.y][point.x].start) {
                    if (findStartPoint == null) {
                        findStartPoint = point;
                    } else {
                        if (findStartPoint.depth < point.depth) {

                        } else {
                            if (point.y < findStartPoint.y) {
                                findStartPoint = point;
                            } else if (point.y == findStartPoint.y) {
                                if (point.x < findStartPoint.x) {
                                    findStartPoint = point;
                                }
                            } else {

                            }
                        }
                    }
                }

                for (int direction = 0; direction < 4; direction++) {
                    int newY = point.y + dy[direction];
                    int newX = point.x + dx[direction];

                    if (newY < 0 || newY >= board.length || newX < 0 || newX >= board[0].length)
                        continue;

                    if (!visited[newY][newX]) {
                        if (board[newY][newX] == null) {
                            visited[newY][newX] = true;
                            queue.add(new Point(newY, newX, point.depth + 1));
                        } else {
                            if (!board[newY][newX].wall) {
                                visited[newY][newX] = true;
                                queue.add(new Point(newY, newX, point.depth + 1));
                            }
                        }
                    }

                }
            }

            // 출발지에 가는 길이 없어
            if (findStartPoint == null) {
                sb.append("-1");
                break;
            }

            // 택시 이동
            curY = findStartPoint.y;
            curX = findStartPoint.x;
            int startNumber = board[curY][curX].startNumber;
            board[curY][curX].start = false;
            board[curY][curX].startNumber = 0;

            // 해당 손님 도착지가 어디냐?
            queue = new LinkedList<>();
            visited = new boolean[board.length][board[0].length];

            queue.add(new Point(curY, curX, 0));
            visited[curY][curX] = true;

            Point findEndPoint = null;
            while (!queue.isEmpty()) {
                Point point = queue.remove();

                if (board[point.y][point.x] != null && !board[point.y][point.x].endNumbers.isEmpty()) {
                    for (int i = 0; i < board[point.y][point.x].endNumbers.size(); i++) {
                        if (board[point.y][point.x].endNumbers.get(i) == startNumber) {
                            findEndPoint = point;
                            break;
                        }
                    }

                }

                for (int direction = 0; direction < 4; direction++) {
                    int newY = point.y + dy[direction];
                    int newX = point.x + dx[direction];

                    if (newY < 0 || newY >= board.length || newX < 0 || newX >= board[0].length)
                        continue;

                    if (!visited[newY][newX]) {
                        if (board[newY][newX] == null) {
                            visited[newY][newX] = true;
                            queue.add(new Point(newY, newX, point.depth + 1));
                        } else {
                            if (!board[newY][newX].wall) {
                                visited[newY][newX] = true;
                                queue.add(new Point(newY, newX, point.depth + 1));
                            }
                        }

                    }

                }
            }

            // 도착지에 가는 길이 없어
            if (findEndPoint == null) {
                sb.append("-1");
                break;
            }

            // 택시 이동
            curY = findEndPoint.y;
            curX = findEndPoint.x;
            board[curY][curX].endNumbers.remove((Object) startNumber);


            energy = energy - findStartPoint.depth - findEndPoint.depth;

            if (energy < 0) {
                sb.append("-1");
                break;
            }

            energy = energy + (findEndPoint.depth) * 2;

        }

        if (sb.length() == 0)
            sb.append(energy);

        System.out.println(sb);
    }
}