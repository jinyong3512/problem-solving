import java.io.*;
import java.util.*;

class Block {
    int color;

    Block(int color) {
        this.color = color;
    }
}

public class Main {

    public static int tmpGroupSize;
    public static int tmpGroup0Count;
    public static int answer = 0;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        Block[][] blocks;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        blocks = new Block[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                blocks[i][j] = new Block(Integer.parseInt(st.nextToken()));
            }
        }

        /////////////////////////////////////////////////////////////////////////

        while (true) {
            // 블록 그룹이 없다면 중지
            int maxGroupSize = 1;
            int maxGroup0Count = 0;
            int findY = -1;
            int findX = -1;
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (blocks[i][j] != null && blocks[i][j].color != -1 && blocks[i][j].color != 0) {
                        if (!visited[i][j]) {
                            boolean[][] visited2 = new boolean[N][N];
                            int colorNumber = blocks[i][j].color;

                            tmpGroupSize = 0;
                            tmpGroup0Count = 0;

                            dfs(blocks, i, j, visited, visited2, colorNumber);

                            if (maxGroupSize < tmpGroupSize) {
                                findY = i;
                                findX = j;
                                maxGroupSize = tmpGroupSize;
                                maxGroup0Count = tmpGroup0Count;
                            } else if (maxGroupSize == tmpGroupSize) {
                                if (maxGroup0Count < tmpGroup0Count) {
                                    findY = i;
                                    findX = j;
                                    maxGroup0Count = tmpGroup0Count;
                                } else if (maxGroup0Count == tmpGroup0Count) {
                                    findY = i;
                                    findX = j;
                                }
                            }
                        }
                    }
                }
            }

            if (maxGroupSize == 1) {
                break;
            }

            dfs2(blocks, findY, findX, blocks[findY][findX].color);
            answer += maxGroupSize * maxGroupSize;

            goDown(N, blocks);

            // 반시계 90도면 시계 270도
            for (int time = 0; time < 3; time++) {
                Block[][] newBlocks = new Block[N][N];
                rotate(blocks, newBlocks);
                blocks = newBlocks;
            }

            goDown(N, blocks);

        }

        System.out.println(answer);
    }

    public static void dfs(Block[][] blocks, int i, int j, boolean[][] visited, boolean[][] visited2, int colorNumber) {
        tmpGroupSize++;
        visited2[i][j] = true;

        if (blocks[i][j].color == 0)
            tmpGroup0Count++;
        else
            visited[i][j] = true;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= blocks.length || newX < 0 || newX >= blocks[0].length)
                continue;

            if (!visited2[newY][newX] && blocks[newY][newX] != null) {
                if (blocks[newY][newX].color == 0 || blocks[newY][newX].color == colorNumber) {
                    dfs(blocks, newY, newX, visited, visited2, colorNumber);
                }
            }
        }

    }

    public static void dfs2(Block[][] blocks, int i, int j, int colorNumber) {

        blocks[i][j] = null;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= blocks.length || newX < 0 || newX >= blocks[0].length)
                continue;

            if (blocks[newY][newX] != null) {
                if (blocks[newY][newX].color == 0 || blocks[newY][newX].color == colorNumber) {
                    dfs2(blocks, newY, newX, colorNumber);
                }
            }
        }

    }

    public static void goDown(int N, Block[][] blocks) {
        // 중력
        int EmptyY = N;
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (blocks[i][j] == null) {
                    EmptyY = i;
                    for (int y = EmptyY - 1; y >= 0; y--) {
                        if (blocks[y][j] == null) {
                            continue;
                        } else if (blocks[y][j].color == -1) {
                            break;
                        } else {
                            blocks[EmptyY][j] = new Block(blocks[y][j].color);
                            blocks[y][j] = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void rotate(Block[][] blocks, Block[][] newBlocks) {
        int N = blocks.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[N - 1 - j][i] != null)
                    newBlocks[i][j] = new Block(blocks[N - 1 - j][i].color);
            }
        }
    }

    public static void print(Block[][] blocks) {

        int N = blocks.length;

        System.out.println("-----------------");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != null) {
                    System.out.print(blocks[i][j].color + " ");
                } else {
                    System.out.print("  ");
                }

            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

}