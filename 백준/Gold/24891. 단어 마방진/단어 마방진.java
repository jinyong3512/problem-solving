import java.io.*;
import java.util.*;

public class Main {

    public static String answer = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++)
            arr[i] = br.readLine();

        /////////////////////////////////////////

        boolean[] visited = new boolean[N];
        Deque<String> deque = new ArrayDeque<>();
        permutation(L, N, arr, visited, 0, deque);

        for(int i = 0 ; i < answer.length() ; i++){
            if(i != 0 && i%L ==0)
                sb.append("\n");
            sb.append(answer.charAt(i));
        }

        if(sb.length()==0)
            sb.append("NONE");
        System.out.println(sb);

    }

    public static void permutation(int L, int N, String[] arr, boolean[] visited, int depth, Deque<String> deque) {

        if (depth == L) {

            if (can(L, deque)) {

                String curStr = "";
                for (String value : deque)
                    curStr += value;

                if (answer.isEmpty())
                    answer = curStr;
                else if (answer.compareTo(curStr) > 0)
                    answer = curStr;

            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            deque.addLast(arr[i]);
            permutation(L, N, arr, visited, depth + 1, deque);
            deque.removeLast();
            visited[i] = false;
        }


    }

    public static boolean can(int L, Deque<String> deque) {

        char[][] curArr = new char[L][L];

        int i = 0;
        for (String value : deque) {
            for (int j = 0; j < L; j++)
                curArr[i][j] = value.charAt(j);
            i++;
        }

        for (int t = 0; t < L; t++) {
            for (int k = 0; k < L; k++) {
                if (curArr[t][k] != curArr[k][t])
                    return false;
            }
        }
        return true;
    }
}
