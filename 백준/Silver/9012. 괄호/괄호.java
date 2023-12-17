import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            Stack<Character> stack = new Stack<>();

            String inputLine = br.readLine();

            boolean can = true;

            for (int i = 0; i < inputLine.length(); i++) {
                char curChar = inputLine.charAt(i);

                if (curChar == '(') {
                    stack.push('(');
                } else {
                    if (stack.isEmpty()) {
                        can = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (!stack.isEmpty())
                can = false;

            if (can)
                sb.append("YES");
            else
                sb.append("NO");

            sb.append("\n");

        }

        System.out.println(sb);


    }
}