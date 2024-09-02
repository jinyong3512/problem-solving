import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

//        solve1(br, sb, null);
        solve2(br, sb, null);


    }

    public static void solve1(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] diff = new int[367];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            diff[start]++;
            diff[end + 1]--;
        }

        //////////////////////////////////////////////////

        int[] arr = new int[366];
        for (int i = 1; i <= 365; i++) {
            arr[i] = arr[i - 1] + diff[i];
        }

        int answer = 0;

        for (int i = 1; i <= 365; i++) {
            if (arr[i] == 0)
                continue;

            int maxValue = 0;
            int count = 0;
            while (true) {
                if (i > 365)
                    break;

                if (arr[i] == 0)
                    break;

                count++;
                maxValue = Math.max(maxValue, arr[i]);
                i++;
            }

            answer += maxValue * count;

        }

        System.out.println(answer);
    }

    public static void solve2(BufferedReader br, StringBuilder sb, StringTokenizer st) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] dates = new int[366];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (int j = start; j <= end; j++)
                dates[j]++;
        }

        int answer = 0;

        for (int i = 1; i <= 365; i++) {
            if (dates[i] == 0)
                continue;

            int maxValue = 0;
            int count = 0;
            while (true) {
                if (i > 365)
                    break;

                if (dates[i] == 0)
                    break;

                count++;
                maxValue = Math.max(maxValue, dates[i]);
                i++;
            }

            answer += maxValue * count;

        }

        System.out.println(answer);
    }
}