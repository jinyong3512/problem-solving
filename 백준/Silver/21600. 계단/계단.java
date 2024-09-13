import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            heights[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////////

        int[] answers = new int[N];
        for (int i = 0; i < N; i++) {
            answers[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            int answerCandidate = answers[i - 1] + 1;

            if (heights[i] >= answerCandidate) {
                answers[i] = answerCandidate;
            } else {
                answers[i] = heights[i];
            }
        }

        int answer = 0;

        for (int value : answers)
            answer = Math.max(answer, value);

        System.out.println(answer);

    }
}