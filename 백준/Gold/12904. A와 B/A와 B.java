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

        while (true) {

            if (T.equals(S)) {
                sb.append("1");
                break;
            }

            if (T.equals("")) {
                break;
            }

            if (T.charAt(T.length() - 1) == 'A') {
                T = T.substring(0, T.length() - 1);
            } else {

                String tmp = "";

                for (int i = T.length() - 2; i >= 0; i--) {
                    tmp += T.charAt(i);
                }

                T = tmp;

            }

        }

        if (sb.length() == 0) {
            sb.append("0");
        }

        System.out.println(sb);

    }
}