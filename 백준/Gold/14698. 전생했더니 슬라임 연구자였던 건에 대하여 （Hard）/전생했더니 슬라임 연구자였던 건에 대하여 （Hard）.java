import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 2 3 8 10 14

        // 2*3 * 6 * 8 * 10 * 14 * 140 * 48 =

        // 6 8 10 14

        // 48 10 14

        // 140 48

        //

        int T;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pQ = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pQ.add(Long.parseLong(st.nextToken()));
            }

            long answer = 1;

            while (true) {
                long number1 = pQ.remove();

                if (pQ.isEmpty())
                    break;

                long number2 = pQ.remove();

                answer = (answer * ((number1 * number2) % 1000000007)) % 1000000007;

                pQ.add(number1 * number2);
            }

            sb.append(answer % 1000000007).append("\n");

        }

        System.out.println(sb);


    }
}
