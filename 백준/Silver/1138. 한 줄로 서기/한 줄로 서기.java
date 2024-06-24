import java.util.*;
import java.io.*;

public class Main {

    public static int[] realAnswer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 다 구해봐야 알겠는데?

        int N;
        int[] arr;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////
        realAnswer = new int[N];
        int[] answer = new int[N];
        boolean[] visited = new boolean[N];
        permutation(N, arr, answer, visited, 0);

        for(int i =0 ; i < N ; i++){
            sb.append(realAnswer[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void permutation(int N, int[] arr, int[] answer, boolean[] visited, int curIndex) {

        if(curIndex == N){

            for(int i = 0 ; i < N ; i++){
                int count = 0;
                for(int j = 0 ; j < i ; j++){
                    if(answer[j] > answer[i])
                        count++;
                }
                if(count != arr[answer[i]])
                    return;
            }

            for(int i =0 ; i < N ; i++){
                realAnswer[i] = answer[i]+1;
            }

            return;
        }

        for(int i = 0 ; i < N ; i++){
            if(!visited[i]){
                visited[i] = true;
                answer[curIndex] = i;
                permutation(N,arr,answer,visited,curIndex+1);
                visited[i] = false;
            }
        }

    }
}