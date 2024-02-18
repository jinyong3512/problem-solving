import java.io.*;
import java.util.*;

class Data {
    String str;
    boolean correctDirection;

    Data(String str, boolean correctDirection) {
        this.str = str;
        this.correctDirection = correctDirection;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // S를 T로 만들자
        // S 뒤에 A 추가
        // S 뒤에 B 추가하고 문자열 뒤집기

        String S, T;
        S = br.readLine();
        T = br.readLine();

        ///////////////////////////////////////////////////////////////////////////

        String SReverse = "";
        for (int i = S.length() - 1; i >= 0; i--) {
            SReverse += S.charAt(i);
        }

        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(T, true));


        boolean answer = false;

        while (!queue.isEmpty()) {
            Data tmp = queue.remove();

            if (tmp.str.equals(""))
                continue;

            if (tmp.correctDirection) {
                if (tmp.str.equals(S)) {
                    answer = true;
                    break;
                }
            } else {
                if (tmp.str.equals(SReverse)) {
                    answer = true;
                    break;
                }
            }

            if (tmp.correctDirection) {
                if (tmp.str.charAt(tmp.str.length() - 1) == 'A') {
                    queue.add(new Data(tmp.str.substring(0, tmp.str.length() - 1), true));
                }
                if (tmp.str.charAt(0) == 'B') {
                    queue.add(new Data(tmp.str.substring(1, tmp.str.length()), false));
                }
            } else {
                if (tmp.str.charAt(0) == 'A') {
                    queue.add(new Data(tmp.str.substring(1, tmp.str.length()), false));
                }
                if (tmp.str.charAt(tmp.str.length() - 1) == 'B') {
                    queue.add(new Data(tmp.str.substring(0, tmp.str.length() - 1), true));
                }
            }

        }

        if (answer)
            System.out.println("1");
        else
            System.out.println("0");

    }
}
