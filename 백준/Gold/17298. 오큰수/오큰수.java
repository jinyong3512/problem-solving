import java.io.*;
import java.util.*;

class Data {
    int value;
    int index;

    Data(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N이 100만이다 10^6  NlogN 가능

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////////////

        int[] answer = new int[N];

        Stack<Data> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (stack.isEmpty()) {
                stack.push(new Data(arr[i], i));
            } else {
                if (stack.peek().value >= arr[i]) {
                    stack.push(new Data(arr[i], i));
                } else {
                    while (!stack.isEmpty() && stack.peek().value < arr[i]) {
                        answer[stack.pop().index] = arr[i];
                    }
                    stack.push(new Data(arr[i], i));
                }
            }
        }

        while (!stack.isEmpty()) {
            answer[stack.pop().index] = -1;
        }

        for (int i = 0; i < answer.length; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }
}