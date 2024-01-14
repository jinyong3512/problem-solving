import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] heights;

        N = Integer.parseInt(br.readLine());
        heights = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < heights.length; i++)
            heights[i] = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////////////

        int[] answers = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {

            // 오른쪽으로 검사
            double rightMaxAngle = Integer.MIN_VALUE;
            for (int j = i + 1; j < heights.length; j++) {
                double rightTmpAngle = (double) (heights[j] - heights[i]) / (j - i);

                if (rightTmpAngle > rightMaxAngle) {
                    answers[i]++;
                    rightMaxAngle = rightTmpAngle;
                }
            }

            // 왼쪽으로 검사
            double leftMinAngle = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double leftTmpAngle = (double) (heights[i] - heights[j]) / (i - j);

                if (leftTmpAngle < leftMinAngle) {
                    answers[i]++;
                    leftMinAngle = leftTmpAngle;
                }
            }

        }

        int answer = 0;
        for (int i = 0; i < answers.length; i++) {
            answer = Math.max(answer, answers[i]);
        }
        System.out.println(answer);


    }
}