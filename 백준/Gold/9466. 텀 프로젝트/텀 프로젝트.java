import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            int n;
            int[] arr;

            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            ////////////////////////////////////////////////

            int[] success = new int[n];
            for (int i = 0; i < n; i++) {
                if (success[i] == 0) {
                    Stack<Integer> stack = new Stack<>();
                    dfs(arr, i, stack, success);

                    while(!stack.isEmpty()){
                        success[stack.pop()]=-1;
                    }
                }
            }

            int answer = 0;
            for(int i =0 ; i < success.length ; i++){
                if(success[i]==-1)
                    answer+=1;
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);

    }

    public static void dfs(int[] arr, int cur, Stack<Integer> stack, int[] success) {

        // 팀을 못 이루는 애 한테 갔네?
        if (success[cur] == -1) {
            return;
        }
        // 새친구
        else if (success[cur] == 0) {
            stack.push(cur);
            success[cur] = 1;
            dfs(arr, arr[cur], stack, success);
        }
        // 내가 지금 돌고 있는 친구
        else if (success[cur] == 1) {
            while (stack.peek() != cur) {
                success[stack.pop()]=2;
            }
            success[stack.pop()]=2;
        }
        // 이미 팀을 이룬 애
        else {
            return;
        }

    }
}