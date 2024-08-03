import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input = br.readLine();
        String bomb = br.readLine();

        /////////////////////////////

        Deque<Character> start = new ArrayDeque<>();
        Deque<Character> end = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++)
            start.addLast(input.charAt(i));
        
        ///////////////////////////////////////////

        while (!start.isEmpty()) {
            int i = 0;
            for (; i < bomb.length(); i++) {
                if (start.isEmpty())
                    break;

                end.addLast(start.peekFirst());
                if (start.removeFirst() != bomb.charAt(i)) {
                    break;
                }
            }

            if (i == bomb.length()) {
                
                // 다르다
                for (int j = 0; j < bomb.length(); j++)
                    end.removeLast();
                
                // 같다
                for (int j = 0; j < bomb.length(); j++) {
                    if (end.isEmpty()) {
                        break;
                    } else {
                        start.addFirst(end.removeLast());
                    }
                }
            } else {
                if(start.isEmpty())
                    break;
                // 같다
                for (int j = 0; j < i; j++) {
                    start.addFirst(end.removeLast());
                }
            }

        }

        if (end.isEmpty())
            sb.append("FRULA");
        while (!end.isEmpty())
            sb.append(end.removeFirst());
        System.out.println(sb);


    }
}
