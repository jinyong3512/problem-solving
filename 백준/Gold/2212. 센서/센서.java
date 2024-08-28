import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 1 3 6 6 7 9

        // 123 -> 2
        // 6789 -> 3

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        Arrays.sort(arr);

        // N개 중에 K개를 조합으로 골라서 최솟값을 구할 수 있지만
        // 흠,,,,,,,,,,,,,,,,,,,,,,,
        // 모르겠다 ㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷ

        // 1 3 6 6 7 9
        // 2 3 0 1 2

        int[] gaps = new int[N-1];
        for(int i =0 ; i < N-1 ; i++)
            gaps[i] = arr[i+1]-arr[i];

        Arrays.sort(gaps);

        int answer = 0;
        for(int i = 0 ; i < gaps.length - K + 1 ; i++)
            answer += gaps[i];

        System.out.println(answer);

    }
}