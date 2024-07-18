import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        /////////////////////////////////////////////

        int[] groupNumber = new int[N + 1];
        int groupNumberCount = 1;

        for (int i = 1; i <= N; i++) {

            if (groupNumber[i] != 0)
                continue;

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];

            queue.add(i);
            visited[i] = true;

            while (true) {
                int curIndex = queue.remove();
                int nextIndex = arr[curIndex];

                if (nextIndex == i) {
                    for (int j = 1; j <= N; j++) {
                        if (visited[j])
                            groupNumber[j] = groupNumberCount;
                    }
                    groupNumberCount++;
                    break;
                }

                if(visited[nextIndex]) {
                    groupNumber[i] = -1;
                    break;
                }

                if (groupNumber[nextIndex] == -1) {
                    groupNumber[i] = -1;
                    break;
                } else if (groupNumber[nextIndex] == 0) {
                    queue.add(nextIndex);
                    visited[nextIndex] = true;
                } else {
                    groupNumber[i] = -1;
                    break;
                }
            }
        }

        int answer = 0;
        for(int i =1 ; i <= N ; i++){
            if(groupNumber[i] != -1) {
                answer++;
                sb.append(i).append("\n");
            }
        }
        System.out.println(answer);
        System.out.println(sb);
    }
}
