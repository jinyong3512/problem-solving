import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 최소 이동횟수를 찾는 프로그램

        // N 이 5 * 10^5

        // 파란색을 움직이자
        // 파란색을 왼쪽에 모으는 경우
        // 파란색을 오른쪽에 모으는 경우

        // 빨간색을 움직이자
        // 빨간색을 왼쪽에 모으는 경우
        // 빨간색을 오른쪽에 모으는 경우


        // 4번
        // 4번
        // 4번
        // 2번

        int N = Integer.parseInt(br.readLine());
        String inputLine = br.readLine();

        int answer = Integer.MAX_VALUE;

        int count = 0;
        boolean redFind = false;
        for (int i = 0; i < inputLine.length(); i++) {
            if (!redFind) {
                if (inputLine.charAt(i) == 'R') {
                    redFind = true;
                }
            } else {
                if (inputLine.charAt(i) == 'B') {
                    count++;
                }
            }
        }
        answer = Math.min(answer, count);

        count = 0;
        redFind = false;
        for (int i = inputLine.length() - 1; i >= 0; i--) {
            if (!redFind) {
                if (inputLine.charAt(i) == 'R') {
                    redFind = true;
                }
            } else {
                if (inputLine.charAt(i) == 'B') {
                    count++;
                }
            }
        }
        answer = Math.min(answer, count);

        count = 0;
        boolean blueFind = false;
        for (int i = 0; i < inputLine.length(); i++) {
            if (!blueFind) {
                if (inputLine.charAt(i) == 'B') {
                    blueFind = true;
                }
            } else {
                if (inputLine.charAt(i) == 'R') {
                    count++;
                }
            }
        }
        answer = Math.min(answer, count);

        count = 0;
        blueFind = false;
        for (int i = inputLine.length() - 1; i >= 0; i--) {
            if (!blueFind) {
                if (inputLine.charAt(i) == 'B') {
                    blueFind = true;
                }
            } else {
                if (inputLine.charAt(i) == 'R') {
                    count++;
                }
            }
        }
        answer = Math.min(answer, count);

        System.out.println(answer);
    }
}
