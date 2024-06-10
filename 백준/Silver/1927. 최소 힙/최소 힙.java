import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        PriorityQueue<Long> pQ = new PriorityQueue<>();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            long x = Long.parseLong(br.readLine());

            if (x == 0) {

                if (pQ.isEmpty()) {
                    sb.append("0").append("\n");
                } else {
                    sb.append(pQ.remove()).append("\n");
                }
            } else {

                pQ.add(x);

            }
        }

        System.out.println(sb);


    }
}