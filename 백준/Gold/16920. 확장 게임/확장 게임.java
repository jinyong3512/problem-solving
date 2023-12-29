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

    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N 10^3   M 10^3  P 9
        // Si 10^9

        int N, M, P;
        int[] S;
        int[][] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        S = new int[P];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S.length; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        arr = new int[N][M];
        for (int i = 0; i < arr.length; i++) {
            String line = br.readLine();
            for (int j = 0; j < arr[i].length; j++) {
                if (line.charAt(j) == '#') {
                    arr[i][j] = -1;
                } else if (line.charAt(j) == '.') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = line.charAt(j) - '0';
                }
            }
        }

        ///////////////////////////////////////////

        ArrayList<Queue<Data>> bigArrayList = new ArrayList<>();
        for (int i = 0; i <= P; i++) {
            bigArrayList.add(new LinkedList<>());
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == -1) {

                } else if (arr[i][j] == 0) {

                } else bigArrayList.get(arr[i][j]).add(new Data(i, j, 0));
            }
        }


        while (true) {

            ArrayList<Queue<Data>> newBigArrayList = new ArrayList<>();
            for (int i = 0; i <= P; i++) {
                newBigArrayList.add(new LinkedList<>());
            }

            for (int i = 1; i <= P; i++) {
                while (!bigArrayList.get(i).isEmpty()) {
                    Data tmp = bigArrayList.get(i).remove();

                    if (tmp.depth == S[i - 1]) {
                        newBigArrayList.get(i).add(new Data(tmp.y, tmp.x, 0));
                        continue;
                    }

                    for (int direction = 0; direction < 4; direction++) {
                        int newY = tmp.y + dy[direction];
                        int newX = tmp.x + dx[direction];

                        if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length) continue;

                        if (arr[newY][newX] == -1) {
                            continue;
                        } else if (arr[newY][newX] == 0) {
                            arr[newY][newX] = i;
                            bigArrayList.get(i).add(new Data(newY, newX, tmp.depth + 1));
                        } else if (arr[newY][newX] == i) {
                            continue;
                        } else {
                            continue;
                        }
                    }
                }
            }

            boolean canStop = true;
            for (int i = 0; i <= P; i++) {
                if (newBigArrayList.get(i).size() != 0)
                    canStop = false;
            }

            if (canStop)
                break;

            bigArrayList = newBigArrayList;
        }


        int[] answers = new int[P];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != -1 && arr[i][j] != 0) {
                    answers[arr[i][j] - 1]++;
                }
            }
        }

        for (int i = 0; i < answers.length; i++)
            sb.append(answers[i]).append(" ");

        System.out.println(sb);

    }


}