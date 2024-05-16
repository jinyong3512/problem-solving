import java.io.*;
import java.util.*;

public class Main {

    public static int answer = 0;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 2개 이상 고르기
        // 합이 L 보다 크거나 같고 R보다 작거나 같기
        // 가장 어려운 문제 - 가장 쉬운 문제 >= X

        int N, L, R, X;
        int[] arr;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        powerSet(arr, -1, N, L, R, X, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0);

        System.out.println(answer);
//        System.out.println(count);

    }

    public static void powerSet(int[] arr, int index, int N, int L, int R, int X, int min, int max, int depth, int sum) {

        if (depth >= 2) {
            if (sum >= L) {
                if (sum <= R) {
                    if (max - min >= X) {
//                        System.out.println(max + " " + min);
                        answer++;
                    }
                }
            }
        }

        for (int i = index + 1; i < arr.length; i++) {
            powerSet(arr, i, N, L, R, X, Math.min(min, arr[i]), Math.max(max, arr[i]), depth + 1, sum + arr[i]);
        }


    }
}
