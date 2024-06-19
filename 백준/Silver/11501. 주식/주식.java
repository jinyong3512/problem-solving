import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 산다.
        // 원하는만큼 판다.
        // 아무것도 안한다.

        // 앞에서 읽으면 해당 날짜에 팔아야 최대이득일지 최소이득일지 모름
        // 뒤에서 읽으면 최댓값 아래면 이득 보는 시스템 더 큰 값이 나오면 갱신


        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long answer = 0;
            int maxValue = arr[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                if (arr[i] > maxValue) {
                    maxValue = arr[i];
                } else if (arr[i] == maxValue) {
                    continue;
                } else {
                    answer += (maxValue-arr[i]);
                }
            }

            sb.append(answer).append("\n");

        }

        System.out.println(sb);

    }
}