import java.io.*;
import java.util.*;

// 17 : 20 시작

class Point {
    int y, x;
    int depth;
    int color;

    Point(int y, int x, int depth, int color) {
        this.y = y;
        this.x = x;
        this.depth = depth;
        this.color = color;
    }
}

public class Main {

    public static int answer = Integer.MIN_VALUE;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M, G, R;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        // 피울 수 있는 꽃의 최대 개수

        recursion(arr, R, G, -1);

        System.out.println(answer);

    }

    public static void recursion(int[][] arr, int curR, int curG, int index) {

        if (curR == 0 && curG == 0) {
            // 시뮬레이션 해보기

//            for (int i = 0; i < arr.length; i++) {
//                for (int j = 0; j < arr[0].length; j++) {
//                    System.out.print(arr[i][j] + " ");
//                }
//                System.out.println();
//            }

            int[][] newArr = new int[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    newArr[i][j] = arr[i][j];
                }
            }

            spread(newArr);

            return;
        } else if (curR < 0 || curG < 0) {
            return;
        }

        for (int i = index + 1; i < arr.length * arr[0].length; i++) {
            int y = i / arr[0].length;
            int x = i % arr[0].length;

            if (arr[y][x] != 0 && arr[y][x] != 1) {
                arr[y][x] = 3;
                recursion(arr, curR - 1, curG, i);
                arr[y][x] = 2;

                arr[y][x] = 4;
                recursion(arr, curR, curG - 1, i);
                arr[y][x] = 2;
            }

        }
    }

    public static void spread(int[][] arr) {
        // 3이 레드
        // 4가 그린

        int[][] times = new int[arr.length][arr[0].length];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0 && arr[i][j] != 1 && arr[i][j] != 2) {
                    queue.add(new Point(i, j, 0, arr[i][j]));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point tmp = queue.remove();

            if (arr[tmp.y][tmp.x] == 5)
                continue;

            for (int direction = 0; direction < 4; direction++) {
                int newY = tmp.y + dy[direction];
                int newX = tmp.x + dx[direction];

                if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                    continue;

                if (arr[newY][newX] == 0)
                    continue;
                else if (arr[newY][newX] == 1 || arr[newY][newX] == 2) {
                    arr[newY][newX] = tmp.color;
                    times[newY][newX] = tmp.depth + 1;
                    queue.add(new Point(newY, newX, tmp.depth + 1, tmp.color));
                } else if (arr[newY][newX] == 5) {
                    continue;
                } else if (arr[newY][newX] == tmp.color) {
                    continue;
                } else {
                    if (tmp.depth + 1 == times[newY][newX]) {
                        arr[newY][newX] = 5;
                    }
                }

            }

        }

        int tmpAnswer = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 5)
                    tmpAnswer++;
            }
        }

        answer = Math.max(answer, tmpAnswer);

    }
}