import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 대문자로 이루어진 두 단어
        // 2가지 조건을 만족하면 "같은 구성"
        // 두 개의 단어가 같은 종류의 문자로 이루어져 있다
        // 같은 문자는 같은 개수 만큼 있다

        // 비슷한 단어
        // 1. 같은 구성
        // 2. 한 단어에서 한 문자를 더한다
        // 3. 한 단어에서 한 문자를 뺀다
        // 4. 하나의 문자를 다른 문자로 바꿔서 같은 구성이면

        int N;
        String str;
        int[] arr = new int[26];

        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'A']++;
        }

        int answer = 0;

        for (int i = 0; i < N - 1; i++) {

            int[] gap = new int[26];
            for (int j = 0; j < 26; j++) {
                gap[j] = arr[j];
            }

            str = br.readLine();

            for (int j = 0; j < str.length(); j++) {
                char nowChar = str.charAt(j);
                gap[nowChar - 'A']--;
            }

            boolean can = true;
            int minusCount = 0;
            int plusCount = 0;
            for (int j = 0; j < 26; j++) {

                if (gap[j] == 0)
                    continue;

                else if (gap[j] == -1) {
                    minusCount++;

                } else if (gap[j] == 1) {
                    plusCount++;
                } else {
                    can = false;
                }

            }

            if (!can)
                continue;

            if (minusCount == 1) {
                if (plusCount == 0 || plusCount == 1) {
                    answer++;
                }
            } else if (minusCount == 0) {
                if (plusCount == 0 || plusCount == 1) {
                    answer++;
                }
            }

//            System.out.println("minusCount =" + minusCount);
//            System.out.println("plusCount =" + plusCount);


        }
        System.out.println(answer);
    }
}
