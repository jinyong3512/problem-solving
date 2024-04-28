import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////

        // 다른 두 개의 합으로 나타나면 좋다

        // 수의 위치가 다르면 값이 같아도 다른 수이다

        int answer = 0;

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {

            boolean find = false;

            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;

                int findValue = arr[i] - arr[j];

                int lowerboundIndex = lowerbound(arr, findValue);
                int upperboundIndex = upperbound(arr, findValue);

                if (lowerboundIndex <= i && i <= upperboundIndex - 1) {
                    if (lowerboundIndex <= j && j <= upperboundIndex - 1) {
                        if (upperboundIndex - lowerboundIndex > 2) {
                            find = true;
                        }
                    } else {
                        if (upperboundIndex - lowerboundIndex > 1) {
                            find = true;
                        }
                    }
                } else if (lowerboundIndex <= j && j <= upperboundIndex - 1) {
                    if (upperboundIndex - lowerboundIndex > 1) {
                        find = true;
                    }
                } else {
                    if (upperboundIndex - lowerboundIndex > 0) {
                        find = true;
                    }
                }
            }

            if (find)
                answer++;

        }
        System.out.println(answer);
    }

    public static int lowerbound(int[] arr, int findValue) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        while (leftIndex <= rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            if (arr[mid] >= findValue) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }

        return leftIndex;
    }

    public static int upperbound(int[] arr, int findValue) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        while (leftIndex <= rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            if (arr[mid] > findValue) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }

        return leftIndex;
    }
}
