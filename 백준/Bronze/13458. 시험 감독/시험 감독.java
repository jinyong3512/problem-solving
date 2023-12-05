import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[] A;
        int B,C;

        //////////////////////////////////////////

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i =0 ; i < N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ///////////////////////////////////////////

        long answer = 0;

        for(int i = 0; i < N ; i++){
            answer++;
            A[i] -= B;
            
            if(A[i]<=0)
                continue;

            if(A[i]%C == 0){
                answer = answer + A[i]/C;
            }else{
                answer = answer + A[i]/C+1;
            }

        }

        System.out.println(answer);

    }
}