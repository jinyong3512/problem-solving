import java.io.*;
import java.util.*;

class Data {
    int y, x, depth, number;

    Data(int y, int x, int depth, int number) {
        this.y = y;
        this.x = x;
        this.depth = depth;
        this.number = number;
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

        PriorityQueue<Data> priorityQueue = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.number == o2.number)
                    return o1.depth - o2.depth;
                return o1.number - o2.number;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == -1) {

                } else if (arr[i][j] == 0) {

                } else priorityQueue.add(new Data(i, j, 0, arr[i][j]));
            }
        }


        while (true) {

            PriorityQueue<Data> newPriorityQueue = new PriorityQueue<>(new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    if (o1.number == o2.number)
                        return o1.depth - o2.depth;
                    return o1.number - o2.number;
                }
            });

            while (!priorityQueue.isEmpty()) {
                Data tmp = priorityQueue.remove();

                if (tmp.depth == S[tmp.number - 1]) {
                    newPriorityQueue.add(new Data(tmp.y, tmp.x, 0, tmp.number));
                    continue;
                }

                for (int direction = 0; direction < 4; direction++) {
                    int newY = tmp.y + dy[direction];
                    int newX = tmp.x + dx[direction];

                    if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length) continue;

                    if (arr[newY][newX] == -1) {
                        continue;
                    } else if (arr[newY][newX] == 0) {
                        arr[newY][newX] = tmp.number;
                        priorityQueue.add(new Data(newY, newX, tmp.depth + 1, tmp.number));
                    } else if (arr[newY][newX] == tmp.number) {
                        continue;
                    } else {
                        continue;
                    }
                }
            }

            if (newPriorityQueue.size() == 0)
                break;
            else
                priorityQueue = newPriorityQueue;
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