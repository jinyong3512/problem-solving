import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // MCN

        int T;

        T = Integer.parseInt(br.readLine());

        for(int t= 0 ; t < T ; t++){

            int N,M;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            BigInteger parent = new BigInteger("1");
            BigInteger child = new BigInteger("1");

            for(int i = M ; i >= M-N+1 ; i--)
                parent = parent.multiply(BigInteger.valueOf(i));

            for(int i = N ; i >=1 ; i--)
                child = child.multiply(BigInteger.valueOf(i));

            sb.append(parent.divide(child)).append("\n");

        }

        System.out.println(sb);

    }
}