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

            double minSlope = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double curSlope = (double) (heights[i] - heights[j]) /(i - j);

                if(minSlope > curSlope){
                    answers[i]++;
                    minSlope = curSlope;
                }
            }

            double maxSlope = Integer.MIN_VALUE;
            for (int j = i + 1; j < N; j++) {
                double curSlope = (double) (heights[j] - heights[i]) /(j - i);

                if(maxSlope < curSlope){
                    answers[i]++;
                    maxSlope = curSlope;
                }
            }
        }

        int answer = 0;
        for(int value:answers)
            answer = Math.max(answer,value);
        System.out.println(answer);

    }
}
