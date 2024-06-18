import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t;
        t = Integer.parseInt(br.readLine());

        long[] minDp = new long[101];
        for (int i = 0; i <= 100; i++)
            minDp[i] = Long.MAX_VALUE;

        // 2개 -> 1
        // 3개 -> 7
        // 4개 -> 4
        // 5개 -> 2, 3, 5
        // 6개 -> 6, 9, 0
        // 7개 -> 8

        minDp[0] = -1;
        minDp[1] = -1;
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;

        for (int i = 9; i <= 100; i++) {
            minDp[i] = Math.min(minDp[i], minDp[i - 2] * 10 + 1);
            minDp[i] = Math.min(minDp[i], minDp[i - 3] * 10 + 7);
            minDp[i] = Math.min(minDp[i], minDp[i - 4] * 10 + 4);
            minDp[i] = Math.min(minDp[i], minDp[i - 5] * 10 + 2);
            minDp[i] = Math.min(minDp[i], minDp[i - 6] * 10 + 0);
            minDp[i] = Math.min(minDp[i], minDp[i - 7] * 10 + 8);
        }


        for (int T = 0; T < t; T++) {

            int n = Integer.parseInt(br.readLine());

            sb.append(minDp[n]).append(" ");

            Deque<Integer> maxDeque = new ArrayDeque<>();

            while (true) {
                if (n >= 2) {
                    n -= 2;
                    maxDeque.addLast(1);
                } else {
                    break;
                }
            }
            if (n == 1) {
                maxDeque.removeFirst();
                maxDeque.addFirst(7);
            }

            while (!maxDeque.isEmpty()) {
                sb.append(maxDeque.removeFirst());
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }
}