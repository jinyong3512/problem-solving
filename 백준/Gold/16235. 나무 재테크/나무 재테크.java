import java.io.*;
import java.util.*;

class Board {
    PriorityQueue<Integer> trees = new PriorityQueue<>();
    int source = 5;
}

public class Main {

    // 좌상 부터 시계 방향
    public static int[] dy = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dx = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, K;
        int[][] arr;
        Board[][] board;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        board = new Board[N][N];
        for(int i =0 ; i < N ; i++){
            for(int j= 0; j < N ; j++){
                board[i][j] = new Board();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());

            board[y][x].trees.add(age);
        }

        //////////////////////////////////////////////////////

        for (int k = 0; k < K; k++) {
            // 봄, 여름
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    PriorityQueue<Integer> newTrees = new PriorityQueue<>();

                    while (true) {

                        if (board[i][j].trees.isEmpty())
                            break;

                        int age = board[i][j].trees.remove();

                        // 이제 못먹어
                        if (age > board[i][j].source) {
                            board[i][j].trees.add(age);

                            while (!board[i][j].trees.isEmpty()) {
                                board[i][j].source += board[i][j].trees.remove() / 2;
                            }
                            break;
                        } else {
                            board[i][j].source -= age;
                            age += 1;
                            newTrees.add(age);
                        }
                    }

                    board[i][j].trees = newTrees;

                }
            }

            // 가을

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (Integer age : board[i][j].trees) {
                        if (age % 5 == 0) {
                            for (int direction = 0; direction < 8; direction++) {
                                int newY = i + dy[direction];
                                int newX = j + dx[direction];

                                if (newY >= 0 && newY < N && newX >= 0 && newX < N) {
                                    board[newY][newX].trees.add(1);
                                }
                            }
                        }
                    }
                }
            }


            // 겨울

            for(int i =0 ; i < N ; i++){
                for(int j =0 ; j < N ; j++){
                    board[i][j].source += arr[i][j];
                }
            }
        }

        int answer = 0;

        for(int i =0 ; i < N ; i++){
            for(int j =0 ; j < N ; j++){
                answer +=board[i][j].trees.size();
            }
        }

        System.out.println(answer);

    }
}