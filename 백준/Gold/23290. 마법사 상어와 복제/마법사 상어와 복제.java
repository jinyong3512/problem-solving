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

    public static int[] dy = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};

    public static int[] dy2 = new int[]{-1, 0, 1, 0};
    public static int[] dx2 = new int[]{0, -1, 0, 1};

    public static int maxWay;
    public static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int M, S;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[][] board = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            board[fx][fy].add(d);
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;

        Point shark = new Point(sx, sy);

        //////////////////////////////////////////////////////////////////////

        int[][] smells = new int[4][4];

        for (int s = 0; s < S; s++) {

            // 기억하기

            // 모든 물고기가 이동한다.
            ArrayList<Integer>[][] newBoard = new ArrayList[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    newBoard[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < board[i][j].size(); k++) {

                        // 0 자기 방향 7 마지막 방향
                        int direction = 0;
                        for (; direction < 8; direction++) {
                            int fishDirection = (board[i][j].get(k) - direction + 8) % 8;

                            int newY = i + dy[fishDirection];
                            int newX = j + dx[fishDirection];

                            if (newY < 0 || newY >= board.length || newX < 0 || newX >= board[0].length)
                                continue;

                            if (newY == shark.y && newX == shark.x)
                                continue;

                            if (smells[newY][newX] != 0)
                                continue;

                            newBoard[newY][newX].add(fishDirection);
                            break;

                        }

                        if (direction == 8) {
                            newBoard[i][j].add(board[i][j].get(k));
                        }
                    }
                }
            }

            // 상어 길 찾기
            maxWay = 0;
            maxCount = -1;
            boolean[][] visited = new boolean[4][4];
            dfs(newBoard, shark.y, shark.x, 0, 0, 0, visited);

            String way = String.valueOf(maxWay);

            for (int i = 0; i < way.length(); i++) {
                int direction = way.charAt(i) - '0' - 1;

                shark.y = shark.y + dy2[direction];
                shark.x = shark.x + dx2[direction];

                if (newBoard[shark.y][shark.x].size() > 0) {
                    newBoard[shark.y][shark.x] = new ArrayList<>();
                    smells[shark.y][shark.x] = 3;
                }
            }


            // 냄새 감소
            for (int i = 0; i < smells.length; i++) {
                for (int j = 0; j < smells[0].length; j++) {
                    if (smells[i][j] > 0)
                        smells[i][j]--;
                }
            }

            // 5 복제
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    for (int k = 0; k < board[i][j].size(); k++) {
                        newBoard[i][j].add(board[i][j].get(k));
                    }
                }
            }
            
            board = newBoard;

        }

        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                answer += board[i][j].size();
            }
        }

        System.out.println(answer);

    }

    public static void dfs(ArrayList<Integer>[][] board, int i, int j, int depth, int tmpCount, int way, boolean[][] visited) {

        //  상은 1, 좌는 2, 하는 3, 우는 4
        if (depth == 3) {

            if (maxCount < tmpCount) {
                maxCount = tmpCount;
                maxWay = way;
            } else if (maxCount == tmpCount) {
                if (maxWay > way)
                    maxWay = way;
            } else {

            }

            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy2[direction];
            int newX = j + dx2[direction];

            if (newY < 0 || newY >= board.length || newX < 0 || newX >= board[0].length)
                continue;

            if (visited[newY][newX]) {
                dfs(board, newY, newX, depth + 1, tmpCount, way * 10 + (direction + 1), visited);
            } else {
                visited[newY][newX] = true;
                dfs(board, newY, newX, depth + 1, tmpCount + board[newY][newX].size(), way * 10 + (direction + 1), visited);
                visited[newY][newX] = false;
            }


        }
    }

}