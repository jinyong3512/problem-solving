import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        /////////////////////////////////////////////////////////////

        int maxGap = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 1; k < Math.min(arr.length, arr[0].length); k++) {
                    if (function1(arr, i, j, k))
                        maxGap = Math.max(maxGap, k);
                }
            }
        }

        System.out.println((maxGap + 1) * (maxGap + 1));

    }

    public static boolean function1(int[][] arr, int i, int j, int k) {
        int newY = i + k;
        int newX = j + k;

        if (newY < 0 || newY >= arr.length || newX < 0 || newX >= arr[0].length)
            return false;

        if (arr[i][j] != arr[newY][j])
            return false;

        if (arr[i][j] != arr[i][newX])
            return false;

        if (arr[i][j] != arr[newY][newX])
            return false;

        return true;
    }
}