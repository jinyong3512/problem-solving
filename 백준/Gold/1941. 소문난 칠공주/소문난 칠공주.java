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

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 7명 구성
        // 세로가로 반드시 인접
        // 아무 학생이나
        // 그러나 S가 4명 이상이여야 함


        char[][] students = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                students[i][j] = line.charAt(j);
            }
        }

        //////////////////////////////////////////////////////////////////////

        boolean[] visited = new boolean[25];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (students[i][j] == 'S') {
                    combination(students, i * 5 + j, 1, 1, 0, visited);
                } else {
                    combination(students, i * 5 + j, 1, 0, 1, visited);
                }
            }
        }

        System.out.println(answer);
    }

    public static void combination(char[][] students, int index, int depth, int sNumber, int yNumber, boolean[] visited) {
        visited[index] = true;

        if (yNumber >= 4) {

            visited[index] = false;
            return;
        }

        if (depth == 7) {

            int curY = index / 5;
            int curX = index % 5;

            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(curY, curX));

            boolean[][] curVisited = new boolean[5][5];
            curVisited[curY][curX] = true;

            int count = 1;
            while (!queue.isEmpty()) {
                Point curPoint = queue.remove();

                for (int direction = 0; direction < 4; direction++) {
                    int newY = curPoint.y + dy[direction];
                    int newX = curPoint.x + dx[direction];

                    if (newY < 0 || newY >= 5 || newX < 0 || newX >= 5) {
                        continue;
                    }

                    if (curVisited[newY][newX])
                        continue;

                    if (visited[newY * 5 + newX]) {
                        count++;
                        curVisited[newY][newX] = true;
                        queue.add(new Point(newY, newX));
                    }
                }
            }

            if (count == 7) {
                answer++;
            }


            visited[index] = false;
            return;
        }
        for (int i = index + 1; i < 25; i++) {
            if (students[i / 5][i % 5] == 'S') {
                combination(students, i, depth + 1, sNumber + 1, yNumber, visited);
            } else {
                combination(students, i, depth + 1, sNumber, yNumber + 1, visited);
            }
        }
        visited[index] = false;

    }
}