import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());


        Deque<Integer> deque = new ArrayDeque<>();


        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();

            if (order.equals("push"))
                deque.addLast(Integer.parseInt(st.nextToken()));

            else if (order.equals("pop")) {
                if (deque.isEmpty()) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(deque.removeFirst()).append("\n");
                }
            } else if (order.equals("size")) {
                sb.append(deque.size()).append("\n");
            } else if (order.equals("empty")) {
                if (deque.isEmpty()) {
                    sb.append("1").append("\n");
                } else {
                    sb.append("0").append("\n");
                }
            } else if (order.equals("front")) {
                if (deque.isEmpty()) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(deque.peekFirst()).append("\n");
                }
            } else if (order.equals("back")) {
                if (deque.isEmpty()) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(deque.peekLast()).append("\n");
                }
            }

        }

        System.out.println(sb);

    }
}