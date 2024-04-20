import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] arr1;
        int M;
        int[] arr2;

        N = Integer.parseInt(br.readLine());

        arr1 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////

        Arrays.sort(arr1);

        for (int i = 0; i < M; i++) {
            int curNumber = arr2[i];

            int leftIndex = 0;
            int rightIndex = N - 1;

            while (leftIndex <= rightIndex) {
                int mid = (leftIndex + rightIndex) / 2;

                if (arr1[mid] > curNumber) {
                    rightIndex = mid - 1;
                } else if (arr1[mid] == curNumber) {
                    rightIndex = mid - 1;
                } else {
                    leftIndex = mid + 1;
                }
            }

            int underBound = leftIndex;

            leftIndex = 0;
            rightIndex = N - 1;
            while (leftIndex <= rightIndex) {
                int mid = (leftIndex + rightIndex) / 2;

                if (arr1[mid] > curNumber) {
                    rightIndex = mid - 1;
                } else if (arr1[mid] == curNumber) {
                    leftIndex = mid + 1;
                } else {
                    leftIndex = mid + 1;
                }
            }

            int upperBound = rightIndex;

            sb.append(upperBound - underBound+1).append(" ");

        }

        System.out.println(sb);

    }
}
