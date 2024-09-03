import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 양의 정수 무게 C에 딱 맞게 가져오면 전부 만원
        // 최대 3개 중복 선택 불가능 물건들의 무게는 모두 다르다

        //  ex) 1, 2, 3, 4, 5

        // 만 원에 구매할 수 있는 조합이 있는가?

        // 물건의 개수 N 1 ~ 5000
        // 무게 C 1 ~ 10^8

        // 물건 각각의 무게 w

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] weights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////

        /*
        가장 멍청하게 생각
        해당 물건 선택하고 안하고
        2^N
        */

        /*
         * 무게의 모든 경우의 수를 구한다?
         *
         * */

        // 일단 힌트 봄 아 최대 3개

        if (choose1(N, C, weights) || choose2(N, C, weights) || choose3(N, C, weights))
            System.out.println("1");
        else
            System.out.println("0");

    }

    public static boolean choose1(int N, int C, int[] weights) {
        for (int i = 0; i < N; i++) {
            if (weights[i] == C)
                return true;
        }
        return false;
    }

    public static boolean choose2(int N, int C, int[] weights) {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (weights[i] + weights[j] == C)
                    return true;
            }
        }
        return false;
    }

    public static boolean choose3(int N, int C, int[] weights) {

        Arrays.sort(weights);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                int spaceWeight = C - weights[i] - weights[j];

                int leftIndex = 0;
                int rightIndex = N - 1;

                while (leftIndex <= rightIndex) {
                    int curIndex = (leftIndex + rightIndex) / 2;
                    if (weights[curIndex] == spaceWeight) {
                        if (curIndex == i || curIndex == j)
                            break;
                        else
                            return true;
                    }

                    if (weights[curIndex] < spaceWeight)
                        leftIndex = curIndex + 1;
                    else
                        rightIndex = curIndex - 1;
                }

            }
        }
        return false;

    }
}