import java.io.*;
import java.util.*;

public class Main {

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] useChances = new int[][]{
                {
                        0, 4, 3, 3, 4, 3, 2, 3, 1, 2
                },
                {
                        4, 0, 5, 3, 2, 5, 6, 1, 5, 4
                },
                {
                        3, 5, 0, 2, 5, 4, 3, 4, 2, 3
                },
                {
                        3, 3, 2, 0, 3, 2, 3, 2, 2, 1
                },
                {
                        4, 2, 5, 3, 0, 3, 4, 3, 3, 2
                },
                {
                        3, 5, 4, 2, 3, 0, 1, 4, 2, 1
                },
                {
                        2, 6, 3, 3, 4, 1, 0, 5, 1, 2
                },
                {
                        3, 1, 4, 2, 3, 4, 5, 0, 4, 3
                },
                {
                        1, 5, 2, 2, 3, 2, 1, 4, 0, 1
                },
                {
                        2, 4, 3, 1, 2, 1, 2, 3, 1, 0
                }
        };

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());


//        solve1(useChances, N, K, P, X);
        solve2(useChances, N, K, P, X);

    }

    public static void solve1(int[][] useChances, int N, int K, int P, int X) {

        Deque<String> deque = new ArrayDeque<>();
        recursion(N, K, P, X, 0, useChances, deque);

        System.out.println(answer - 1);

    }

    public static void recursion(int N, int K, int P, int X, int depth, int[][] useChances, Deque<String> deque) {
        if (depth == K) {

            String curStr = "";
            for (String value : deque)
                curStr = value + curStr;
            if (1 <= Integer.parseInt(curStr) && Integer.parseInt(curStr) <= N)
                answer++;

            return;
        }

        int curNumber = (int) (X % Math.pow(10, depth + 1) / Math.pow(10, depth));

        for (int j = 0; j <= 9; j++) {
            if (P - useChances[curNumber][j] >= 0) {
                deque.addLast(String.valueOf(j));
                recursion(N, K, P - useChances[curNumber][j], X, depth + 1, useChances, deque);
                deque.removeLast();
            }
        }
    }

    public static void solve2(int[][] useChances, int N, int K, int P, int X) {


        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X)
                continue;

            String XStr = String.valueOf(X);
            String curNumber = String.valueOf(i);

            int gap = Math.abs(XStr.length() - curNumber.length());
            for (int j = 0; j < gap; j++) {
                if (XStr.length() > curNumber.length())
                    curNumber = "0" + curNumber;
                else if (XStr.length() < curNumber.length())
                    XStr = "0" + XStr;
            }

            int curP = 0;
            for (int j = 0; j < XStr.length(); j++) {
                curP += useChances[XStr.charAt(j) - '0'][curNumber.charAt(j) - '0'];
            }

            if (curP <= P)
                answer++;

        }
        System.out.println(answer);

    }
}
