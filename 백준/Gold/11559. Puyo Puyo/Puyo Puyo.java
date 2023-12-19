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

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        char[][] arr = new char[12][6];

        for (int i = 0; i < arr.length; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = inputLine.charAt(j);
            }
        }

        ////////////////////////////////////////////////////////////////////////
        int answer = 0;

        while (true) {

            boolean boom = false;

            boolean[][] visited = new boolean[arr.length][arr[0].length];

            char[][] newArr = new char[arr.length][arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] == '.') newArr[i][j] = '.';
                    else {
                        if (!visited[i][j]) {
                            Queue<Point> queue = new LinkedList<>();
                            dfs(arr, i, j, visited, arr[i][j], queue);
                            if (queue.size() >= 4) {
                                boom = true;
                                while (!queue.isEmpty()) {
                                    Point tmpPoint = queue.remove();
                                    newArr[tmpPoint.y][tmpPoint.x] = '.';
                                }
                            } else {
                                while (!queue.isEmpty()) {
                                    Point tmpPoint = queue.remove();
                                    newArr[tmpPoint.y][tmpPoint.x] = arr[i][j];
                                }
                            }
                        }
                    }
                }
            }

            arr = newArr;

            // 중력
            for (int j = 0; j < arr[0].length; j++) {
                for (int i = arr.length - 1; i >= 0; i--) {
                    if (arr[i][j] == '.') {
                        for (int y = i - 1; y >= 0; y--) {
                            if (arr[y][j] != '.') {
                                arr[i][j] = arr[y][j];
                                arr[y][j] = '.';
                                break;
                            }

                        }
                    }
                }
            }

            if (boom) {
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);

    }

    public static void dfs(char[][] arr, int i, int j, boolean[][] visited, char alphabet, Queue<Point> queue) {
        queue.add(new Point(i, j));
        visited[i][j] = true;

        for (int direction = 0; direction < 4; direction++) {
            int newY = i + dy[direction];
            int newX = j + dx[direction];

            if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
                continue;

            if (!visited[newY][newX] && arr[newY][newX] == alphabet)
                dfs(arr, newY, newX, visited, alphabet, queue);


        }

    }
}