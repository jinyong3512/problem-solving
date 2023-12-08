import java.io.*;
import java.util.*;

class Data {
    int y, x, depth;

    Data(int y, int x, int depth) {
        this.y = y;
        this.x = x;
        this.depth = depth;
    }
}

public class Main {

    public static int answer = Integer.MAX_VALUE;

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        int[][] arr;

        ///////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////////////////////////////

        // TODO : 조합으로 재귀 돌리기
        Deque<Data> deque = new LinkedList<>();
        recursion(arr, 0, M, -1, deque);

        if (answer == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(answer);

    }

    public static void recursion(int[][] arr, int depth, int M, int index, Deque<Data> deque) {
        if (depth == M) {

            Deque<Data> tmpDeque = new LinkedList<>();
            for (Data data : deque) {
                tmpDeque.add(new Data(data.y, data.x, data.depth));
            }

            bfs(arr, tmpDeque);

            return;
        }

        for (int i = index + 1; i < arr.length * arr[0].length; i++) {
            if (arr[i / arr.length][i % arr.length] == 2) {
                deque.addLast(new Data(i / arr.length, i % arr[0].length, 0));
                recursion(arr, depth + 1, M, i, deque);
                deque.removeLast();
            }
        }


    }

    public static void bfs(int[][] arr, Deque<Data> deque) {

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        for (Data data : deque) {
            visited[data.y][data.x] = true;
        }

        ArrayList<Data> arrayList = new ArrayList<>();

        while (!deque.isEmpty()) {
            Data tmpData = deque.removeFirst();

            arrayList.add(tmpData);

            for (int direction = 0; direction < 4; direction++) {
                int newY = tmpData.y + dy[direction];
                int newX = tmpData.x + dx[direction];

                if (newY >= 0 && newY < arr.length && newX >= 0 && newX < arr[0].length) {

                    if (arr[newY][newX] == 0 && !visited[newY][newX]) {
                        visited[newY][newX] = true;
                        deque.add(new Data(newY, newX, tmpData.depth + 1));
                    } else if (arr[newY][newX] == 2 && !visited[newY][newX]) {
                        visited[newY][newX] = true;
                        deque.add(new Data(newY, newX, tmpData.depth + 1));
                    }
                }
            }
        }

        if (!checkEmpty(arr, visited)) {
            int depth = 0;
            for (Data data : arrayList) {
                if (arr[data.y][data.x] == 0) {
                    depth = data.depth;
                }
            }

            answer = Math.min(answer, depth);
        }

    }

    public static boolean checkEmpty(int[][] board, boolean[][] visited) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!visited[i][j] && board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}