import java.io.*;
import java.util.*;

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 2339 시작
        // Deque 쓰면 안되겠당

        int N, M, T;
        int[][] orders;
        int[][] board;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        orders = new int[T][3];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            orders[i][0] = x;
            orders[i][1] = d;
            orders[i][2] = k;
        }

        ///////////////////////////////////////////////////

        for (int t = 0; t < T; t++) {

            //  1 -> 2 -> 3 : 0 -> 1 -> 3 x
            //  2 -> 4 -> 6 :
            for (int i = orders[t][0]; i < board.length; i = i + orders[t][0] + 1) {

                // M번 회전이면 제자리 입니다.
                // 반시계 방향은 M 을 빼면 될것 같은데?
//                int rotation = orders[t][2];
//                rotation %= M;
//
//                if (orders[t][1] == 1) {
//                    rotation = (M - rotation) % M;
//                }

                int rotation = orders[t][2];
                if (orders[t][1] == 1)
                    rotation = M - rotation;

                for (int r = 0; r < rotation; r++) {
                    // 끝 기억
                    int tmp = board[i][board[0].length - 1];
                    // 끝 부터 먹혀라
                    for (int j = board[0].length - 1; j > 0; j--) {
                        board[i][j] = board[i][j - 1];
                    }
                    // 끝에 것은 처음으로 가는거지
                    board[i][0] = tmp;
                }

            }

            int[][] newBoard = new int[board.length][board[0].length];

            boolean action = false;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    newBoard[i][j] = board[i][j];

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = i + dy[direction];
                        int newX = j + dx[direction];

                        if (newY < 0 || newY >= board.length) {
                            continue;
                        }

                        newX += board[0].length;
                        newX %= board[0].length;

                        if (board[i][j] != 0 && board[i][j] == board[newY][newX]) {
                            action = true;
                            newBoard[i][j] = 0;
                        }
                    }
                }
            }

            board = newBoard;

            if (!action) {
                int count = 0;
                int sum = 0;
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[0].length; j++) {
                        if (board[i][j] != 0) {
                            count++;
                            sum += board[i][j];
                        }
                    }
                }

                float average = (float) sum / count;

                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[0].length; j++) {
                        if (board[i][j] != 0) {
                            if (board[i][j] > average)
                                board[i][j]--;
                            else if (board[i][j] < average)
                                board[i][j]++;
                        }
                    }
                }
            }

        }

        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum += board[i][j];
            }
        }
        System.out.println(sum);


    }
}
