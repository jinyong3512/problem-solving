import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String S, T;

        S = br.readLine();
        T = br.readLine();

        /////////////////////////////////////////////////////////////////////////

        int SSize = S.length();
        int TSize = T.length();

        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < TSize; i++) {
            deque.addLast(T.charAt(i));
        }

        while (true) {

            if (deque.size() == SSize) {
                boolean can = true;
                for (int i = 0; i < SSize; i++) {
                    if (deque.removeFirst() != S.charAt(i)) {
                        can = false;
                    }
                }

                if (can) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                System.out.println(sb);
                break;
            }

            if (deque.peekLast() == 'A') {
                deque.removeLast();
            } else {

                deque.removeLast();

                Deque<Character> newDeque = new ArrayDeque<>();

                while (!deque.isEmpty()) {
                    newDeque.addLast(deque.removeLast());
                }

                deque = newDeque;


            }

        }

    }
}