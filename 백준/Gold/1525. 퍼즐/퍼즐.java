import java.io.*;
import java.util.*;

class Data {
    int number;
    int depth;

    Data(int number, int depth) {
        this.number = number;
        this.depth = depth;
    }
}

public class Main {

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //////////////////////////////////////////////////////

        int number = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                number = number + arr[i][j];
                if (i == 2 && j == 2) {

                } else {
                    number = number * 10;
                }
            }
        }

        HashMap<Integer, Boolean> visited = new HashMap<>();
        visited.put(number, true);

        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(number, 0));

        while (!queue.isEmpty()) {
            Data tmp = queue.remove();

//            System.out.println("tmp.depth = " + tmp.depth + " tmp.number = " + tmp.number);

            if (tmp.number == 123456780) {
                sb.append(tmp.depth);
                break;
            }

            String str = String.valueOf(tmp.number);
            if (str.length() == 8)
                str = '0' + str;

            int y = -1;
            int x = -1;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = str.charAt(i * 3 + j) - '0';
                    if (arr[i][j] == 0) {
                        y = i;
                        x = j;
                    }
                }
            }

            for (int direction = 0; direction < 4; direction++) {

                int newY = y + dy[direction];
                int newX = x + dx[direction];

                if (newY < 0 || newX < 0 || newY >= 3 || newX >= 3)
                    continue;

                int swapTmp = arr[y][x];
                arr[y][x] = arr[newY][newX];
                arr[newY][newX] = swapTmp;

                number = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        number = number + arr[i][j];
                        if (i == 2 && j == 2) {

                        } else {
                            number = number * 10;
                        }
                    }
                }

                if (visited.containsKey(number)) {
                    swapTmp = arr[y][x];
                    arr[y][x] = arr[newY][newX];
                    arr[newY][newX] = swapTmp;
                    continue;
                }

                visited.put(number, true);
                queue.add(new Data(number, tmp.depth + 1));
                swapTmp = arr[y][x];
                arr[y][x] = arr[newY][newX];
                arr[newY][newX] = swapTmp;

            }


        }

        if (sb.length() == 0)
            sb.append("-1");

        System.out.println(sb);

    }
}