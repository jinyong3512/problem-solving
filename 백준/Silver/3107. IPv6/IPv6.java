import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input = br.readLine();

        ///////////////////////////////////////////////////////

        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ':')
                count++;
        }

        if (count == 8) {

            for (int i = 0; i < input.length() - 1; i++) {
                if (input.charAt(i) == ':' && input.charAt(i + 1) == ':') {
                    input = input.substring(0, i) + input.substring(i + 1);
                    break;
                }
            }

        } else if (count == 7) {


        } else {

            String plus = "";
            for (int i = 0; i < 7 - count; i++) {
                plus += ":";
            }

            for (int i = 0; i < input.length() - 1; i++) {
                if (input.charAt(i) == ':' && input.charAt(i + 1) == ':') {
                    input = input.substring(0, i) + plus + input.substring(i);
                    break;
                }
            }

        }

        String cur = "";
        int answerIndex = 0;
        String[] answer = new String[8];

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == ':') {
                answer[answerIndex] = cur;
                answerIndex++;
                cur = "";
            } else {
                cur += input.charAt(i);
            }
        }
        answer[answerIndex] = cur;

        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < 4 - answer[i].length(); j++) {
                sb.append("0");
            }
            sb.append(answer[i]);

            if (i == answer.length - 1)
                continue;

            sb.append(":");
        }
        System.out.println(sb);

    }
}