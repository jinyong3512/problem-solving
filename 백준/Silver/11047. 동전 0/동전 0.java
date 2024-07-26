import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        /////////////////////////////////////////

        int answer = 0 ;

        for (int i = N - 1; i >= 0; i--) {
            if(K-values[i] <0)
                continue;

            int curCount = K/values[i];
            answer += curCount;
            K = K - values[i] * curCount;


        }

        System.out.println(answer);

    }
}
