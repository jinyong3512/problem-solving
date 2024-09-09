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

    public static int[] dy = new int[]{0, -1, 0, 1};
    public static int[] dx = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ////////////////////////////////////////////////////


        // solve 1
        int groupNumber = 0;
        int[][] groupNumbers = new int[N][M];

        ArrayList<Integer> groupNumbersCount = new ArrayList<>();
        groupNumbersCount.add(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (groupNumbers[i][j] == 0) {
                    groupNumber++;
                    int curCount = bfs(groupNumbers, arr, i, j, groupNumber);
                    groupNumbersCount.add(curCount);
                }
            }
        }

        System.out.println(groupNumber);

        // solve 2
        int answer2 = 0;
        for (int value : groupNumbersCount)
            answer2 = Math.max(answer2, value);
        System.out.println(answer2);

        // solve 3
        solve3(arr, groupNumbers, groupNumbersCount);

    }

    public static int bfs(int[][] groupNumbers, int[][] arr, int i, int j, int groupNumber) {

        int curCount = 1;
        groupNumbers[i][j] = groupNumber;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));

        while (!queue.isEmpty()) {
            Point curPoint = queue.remove();

            for (int direction = 0; direction < 4; direction++) {
                if (isWallExist(arr[curPoint.y][curPoint.x], direction))
                    continue;

                int newY = curPoint.y + dy[direction];
                int newX = curPoint.x + dx[direction];

                if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                    continue;

                if (groupNumbers[newY][newX] != 0)
                    continue;

                curCount++;
                groupNumbers[newY][newX] = groupNumber;
                queue.add(new Point(newY, newX));
            }
        }
        return curCount;

    }

    public static boolean isWallExist(int number, int direction) {
        if (direction == 0) {
            if (number % 2 == 1)
                return true;
            return false;
        } else if (direction == 1) {
            if (number % 4 / 2 == 1)
                return true;
            return false;
        } else if (direction == 2) {
            if (number % 8 / 4 == 1)
                return true;
            return false;
        } else {
            if (number % 16 / 8 == 1)
                return true;
            return false;
        }
    }

    public static void solve3(int[][] arr, int[][] groupNumbers, ArrayList<Integer> groupNumbersCount) {

        int answer3 = 0;

        for (int i = 0; i < groupNumbers.length; i++) {
            for (int j = 0; j < groupNumbers[0].length; j++) {

                for (int direction = 0; direction < 4; direction++) {
                    if (!isWallExist(arr[i][j], direction))
                        continue;

                    int newY = i + dy[direction];
                    int newX = j + dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                        continue;

                    if (groupNumbers[newY][newX] == groupNumbers[i][j])
                        continue;

                    int curAnswer3 = groupNumbersCount.get(groupNumbers[i][j]) + groupNumbersCount.get(groupNumbers[newY][newX]);
                    answer3 = Math.max(answer3,curAnswer3);
                }

            }
        }

        System.out.println(answer3);

    }
}