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

        int[] answerCounts = new int[N];
        int[] nearIndex = new int[N];
        for (int i = 0; i < N; i++)
            nearIndex[i] = Integer.MAX_VALUE;

        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            answerCounts[i] += stack.size();
            if (!stack.isEmpty())
                nearIndex[i] = stack.peek();

            stack.push(i);
        }

        stack.clear();
        stack.push(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            answerCounts[i] += stack.size();
            if (!stack.isEmpty()) {

                if (nearIndex[i] == Integer.MAX_VALUE)
                    nearIndex[i] = stack.peek();

                else if (i - nearIndex[i] > stack.peek() - i)
                    nearIndex[i] = stack.peek();
            }

            stack.push(i);
        }

        for (int i = 0; i < N; i++) {
            if (answerCounts[i] == 0)
                sb.append(0).append("\n");
            else
                sb.append(answerCounts[i]).append(" ").append(nearIndex[i] + 1).append("\n");
        }

        System.out.println(sb);


    }
}
