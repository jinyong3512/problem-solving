import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 마지막 곡을 연주할 수 있는 볼륨 중 최댓값을 구하는 프록르ㅐㅁ

        // N S M
        // 1~50 0~M 1~1000

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////

        boolean[] volumes = new boolean[M + 1];
        volumes[S] = true;

        for (int i = 0; i < N; i++) {
            boolean[] newVolumes = new boolean[M + 1];

            for (int j = 0; j <= M; j++) {
                if (volumes[j]) {

                    if (j + arr[i] <= M)
                        newVolumes[j + arr[i]] = true;
                    if (j - arr[i] >= 0)
                        newVolumes[j - arr[i]] = true;

                }
            }

            volumes = newVolumes;
        }

        int answer = -1;
        for (int j = M; j >= 0; j--) {
            if (volumes[j]) {
                answer = j;
                break;
            }
        }

        System.out.println(answer);

    }
}