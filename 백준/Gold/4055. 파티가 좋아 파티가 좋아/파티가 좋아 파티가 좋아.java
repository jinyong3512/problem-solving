import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = 0;

        while (true) {
            testCase++;

            int p = Integer.parseInt(br.readLine());

            if (p == 0)
                break;

            int[][] parties = new int[p][2];

            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine());
                parties[i][0] = Integer.parseInt(st.nextToken());
                parties[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(parties, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {

                    int o1Gap = o1[1] - o1[0];
                    int o2Gap = o2[1] - o2[0];

                    if (o1Gap < o2Gap) {
                        return -1;
                    } else if (o1Gap == o2Gap) {
                        return 0;
                    } else {
                        return 1;
                    }

                }
            });

            int[] times = new int[24];
            for (int i = 0; i < 24; i++) {
                times[i] = 2;
            }

            for (int i = 0; i < p; i++) {
                for (int timesIndex = parties[i][0]; timesIndex < parties[i][1]; timesIndex++) {
                    if (times[timesIndex] > 0) {
                        times[timesIndex]--;
                        break;
                    }
                }
            }

            int answer = 0;

            for (int i = 0; i < 24; i++) {
                answer = answer + 2 - times[i];
            }


            //On day d Emma can attend as many as n parties.
            sb.append("On day ").append(testCase).append(" Emma can attend as many as ").append(answer).append(" parties.").append("\n");
        }

        System.out.println(sb);
    }
}