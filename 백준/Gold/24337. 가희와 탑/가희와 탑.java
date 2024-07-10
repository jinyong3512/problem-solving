import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int point = N - 1 - (b - 1);
        arr[point] = Math.max(a, b);


//        arrPrint(arr);

        int tmp = 1;
        for (int i = N - 1; i > point; i--) {
            arr[i] = tmp;
            tmp++;
        }


//        arrPrint(arr);

        if (a == 1) {
            arr[0] = arr[point];
            for (int i = 1; i <= point; i++) {
                arr[i] = 1;
            }
        } else if (a == 2) {
            for (int i = 0; i < point; i++) {
                arr[i] = 1;
            }
        } else {

            tmp = a - 1;
            for (int i = point - 1; i >= 0; i--) {
                arr[i] = tmp;
                if (tmp != 1)
                    tmp--;
            }
        }

//        arrPrint(arr);

        int countA = 0;
        int maxA = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > maxA) {
                maxA = arr[i];
                countA++;
            }
        }

        int countB = 0;
        int maxB = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] > maxB) {
                maxB = arr[i];
                countB++;
            }
        }

        if (countA == a && countB == b) {
            for (int i = 0; i < arr.length; i++)
                sb.append(arr[i]).append(" ");
        } else
            sb.append("-1");

        System.out.println(sb);
    }
}
