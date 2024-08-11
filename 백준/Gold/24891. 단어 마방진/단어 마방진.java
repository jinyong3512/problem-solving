import java.io.*;
import java.util.*;

public class Main {

    public static boolean find = false;

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

        Arrays.sort(arr);

        boolean[] visited = new boolean[N];
        char[][] answer = new char[L][L];
        permutation(L, N, arr, visited, 0, answer);

        if(!find)
            System.out.println("NONE");
        else{
            for(int i =0 ; i < L ; i++){
                for(int j =0 ; j < L ; j++)
                    sb.append(answer[i][j]);
                sb.append("\n");
            }
            System.out.println(sb);
        }

    }

    public static void permutation(int L, int N, String[] arr, boolean[] visited, int depth, char[][] answer) {
        if (depth == L) {
            find = true;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;

            boolean can = true;

            for (int k = 0; k < depth; k++) {
                if (arr[i].charAt(k) != answer[k][depth])
                    can = false;
            }

            if (can) {
                for (int j = 0; j < L; j++) {
                    answer[depth][j] = arr[i].charAt(j);
                }
                visited[i] = true;
                permutation(L, N, arr, visited, depth + 1, answer);

                if(find)
                    return;

                for (int j = 0; j < L; j++) {
                    answer[depth][j] = '0';
                }
                visited[i] = false;
            }

        }


    }
}
