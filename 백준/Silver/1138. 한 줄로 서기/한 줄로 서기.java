import java.util.*;
import java.io.*;

public class Main {

    public static int[] realAnswer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 다 구해봐야 알겠는데?

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////
        // 방법 1
//        realAnswer = new int[N];
//        int[] answer = new int[N];
//        boolean[] visited = new boolean[N];
//        permutation(N, arr, answer, visited, 0);
//
//        for(int i =0 ; i < N ; i++){
//            sb.append(realAnswer[i]).append(" ");
//        }
//        System.out.println(sb);

        // 방법 2
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            int count = arr[i];

            int curCount = 0;
            for (int j = 0; j < N; j++) {

                // 더 큰키가 들어갈 곳이다.
                if (answer[j] == 0) {
                    if (curCount == count) {
                        answer[j] = i + 1;
                        break;
                    } else {
                        curCount++;
                    }
                }
                // 더 작은 키가 이미 들어가 있다.
                else {
                    continue;
                }
            }
        }

        for(int i = 0 ; i < N ; i++)
            sb.append(answer[i]+" ");
        System.out.println(sb);

    }

    public static void permutation(int N, int[] arr, int[] answer, boolean[] visited, int curIndex) {

        if (curIndex == N) {

            for (int i = 0; i < N; i++) {
                int count = 0;
                for (int j = 0; j < i; j++) {
                    if (answer[j] > answer[i])
                        count++;
                }
                if (count != arr[answer[i]])
                    return;
            }

            for (int i = 0; i < N; i++) {
                realAnswer[i] = answer[i] + 1;
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[curIndex] = i;
                permutation(N, arr, answer, visited, curIndex + 1);
                visited[i] = false;
            }
        }

    }
}