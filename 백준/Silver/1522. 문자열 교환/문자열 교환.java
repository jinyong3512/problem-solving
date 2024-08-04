import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String line = br.readLine();

        ////////////////////////////////////////

        int bCount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'b')
                bCount++;
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < line.length(); i++) {

            int tmpAnswer = 0;

            for (int j = 0; j < bCount; j++) {
                if (line.charAt((i + j) % line.length()) == 'a') {
                    tmpAnswer++;
                }
            }

            answer = Math.min(answer,tmpAnswer);
        }
        System.out.println(answer);

    }
}
