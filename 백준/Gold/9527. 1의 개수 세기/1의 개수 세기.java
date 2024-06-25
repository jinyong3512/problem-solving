import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long A, B;

        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        // 결과 계산
        long result = countOnesInRange(A, B);

        // 결과 출력
        System.out.println(result);
    }

    // A부터 B까지의 범위에서 1의 개수를 세는 함수
    public static long countOnesInRange(long A, long B) {
        return countOnes(B) - countOnes(A - 1);
    }

    // 1부터 x까지의 숫자 중 이진수에서 1의 개수를 세는 함수
    public static long countOnes(long x) {
        long count = 0;
        long factor = 1;

        while (factor <= x) {
            long fullBlocks = (x + 1) / (factor * 2) * factor;
            long remainder = Math.max(0, (x + 1) % (factor * 2) - factor);
            count += fullBlocks + remainder;
            factor *= 2;
        }

        return count;
    }
}