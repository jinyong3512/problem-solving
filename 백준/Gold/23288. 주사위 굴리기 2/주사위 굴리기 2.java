import java.io.*;
import java.util.*;

class Dice {
    int top = 1, bottom = 6;
    int front = 2, back = 5;
    int left = 4, right = 3;
    int direction = 1;

    void goLeft() {
        int tmp = top;
        top = right;
        right = bottom;
        bottom = left;
        left = tmp;
    }

    void goRight() {
        int tmp = top;
        top = left;
        left = bottom;
        bottom = right;
        right = tmp;
    }

    void goUp() {
        int tmp = top;
        top = back;
        back = bottom;
        bottom = front;
        front = tmp;
    }

    void goDown() {
        int tmp = top;
        top = front;
        front = bottom;
        bottom = back;
        back = tmp;
    }
}

public class Main {

    // 상우하좌
    public static int[] dy = new int[]{-1, 0, 1, 0};
    public static int[] dx = new int[]{0, 1, 0, -1};

    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, K;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //////////////////////////////////////////////////////

        Dice dice = new Dice();
        int curY = 0;
        int curX = 0;
        int answer = 0;

        for (int k = 0; k < K; k++) {

            int newY = curY + dy[dice.direction];
            int newX = curX + dx[dice.direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length) {
                dice.direction = (dice.direction + 2) % 4;
                newY = curY + dy[dice.direction];
                newX = curX + dx[dice.direction];
            }

            if (dice.direction == 0) {
                dice.goUp();
            } else if (dice.direction == 1) {
                dice.goRight();
            } else if (dice.direction == 2) {
                dice.goDown();
            } else if (dice.direction == 3) {
                dice.goLeft();
            }

            int A = dice.bottom;
            int B = arr[newY][newX];

            if (A > B) {
                dice.direction = (dice.direction + 1) % 4;
            } else if (A == B) {

            } else {
                dice.direction = (dice.direction - 1 + 4) % 4;
            }

            curY = newY;
            curX = newX;

//            System.out.println(curY + " " + curX + " " + dice.direction);

            count = 0;
            boolean[][] visited = new boolean[arr.length][arr[0].length];
            dfs(arr, visited, curY, curX, B);

            answer += B * count;


        }

        System.out.println(answer);


    }

    public static void dfs(int[][] arr, boolean[][] visited, int curY, int curX, int B) {
        count++;
        visited[curY][curX] = true;


        for (int direction = 0; direction < 4; direction++) {
            int newY = curY + dy[direction];
            int newX = curX + dx[direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                continue;

            if (!visited[newY][newX] && arr[newY][newX] == B) {
                dfs(arr, visited, newY, newX, B);
            }

        }
    }
}