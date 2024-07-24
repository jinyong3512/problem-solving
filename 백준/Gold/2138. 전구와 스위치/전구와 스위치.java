import java.io.*;
import java.util.*;

public class Main {

    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        String start = br.readLine();
        String end = br.readLine();

        String start2 = (start.charAt(0) == '0' ? "1" : "0")
                + (start.charAt(1) == '0' ? "1" : "0")
                + start.substring(2);

        if (check(N,start, end))
            System.out.println(answer);
        else if (check(N,start2, end))
            System.out.println(answer+1);
        else
            System.out.println("-1");

    }

    public static boolean check(int N, String cur, String end) {

        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < N; i++)
            arrayList.add(cur.charAt(i));

        answer = 0;

        for (int i = 0; i < N - 2; i++) {
            if (arrayList.get(i) != end.charAt(i)) {
                answer++;
                arrayList.set(i + 1, arrayList.get(i + 1) == '1' ? '0' : '1');
                arrayList.set(i + 2, arrayList.get(i + 2) == '1' ? '0' : '1');
            }
        }

        if (arrayList.get(N - 2) != end.charAt(N - 2)) {
            answer++;
            arrayList.set(N - 1, arrayList.get(N - 1) == '1' ? '0' : '1');
        }

        if(arrayList.get(N-1) == end.charAt(N-1))
            return true;
        else
            return false;
    }
}
