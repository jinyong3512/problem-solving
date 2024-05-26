import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 2 * 10^5  N
        // 5 * 10^5  H

        int N, H;
        int[] bottom;
        int[] top;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        bottom = new int[N / 2];
        top = new int[N / 2];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                bottom[i / 2] = Integer.parseInt(br.readLine());
            } else {
                top[i / 2] = Integer.parseInt(br.readLine());
            }
        }

        ///////////////////////////////////////////////////////

        int[] heightBottom = new int[H+1];
        for (int i = 0; i < bottom.length; i++)
            heightBottom[bottom[i]]++;

        int[] heightTop = new int[H+1];
        for (int i = 0; i < top.length; i++)
            heightTop[top[i]]++;

        int[] accumulateHeightBottom = new int[H+1];
        accumulateHeightBottom[H] = heightBottom[H];
        for (int i = H-1; i >= 0; i--) {
            accumulateHeightBottom[i] = accumulateHeightBottom[i + 1] + heightBottom[i];
        }

        int[] accumulateHeightTop = new int[H+1];
        accumulateHeightTop[H] = heightTop[H];
        for (int i = H-1; i >= 0; i--) {
            accumulateHeightTop[i] = accumulateHeightTop[i + 1] + heightTop[i];
        }

        int answer = Integer.MAX_VALUE;
        int answerCount = 0;

        for(int i = 1 ; i <= H ; i++){

            int collisionCount = accumulateHeightTop[H+1-i] + accumulateHeightBottom[i];
            if(answer > collisionCount){
                answer = collisionCount;
                answerCount= 1;
            }
            else if ( answer == collisionCount){
                answerCount++;
            }
        }

        System.out.println(answer+" "+answerCount);

    }
}
