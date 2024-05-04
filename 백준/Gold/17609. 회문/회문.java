import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            String inputLine = br.readLine();

            if (isPalindrome(inputLine)) {
                sb.append("0").append("\n");
            } else if (isLeftDiePseudoPalindrome(inputLine)) {
                sb.append("1").append("\n");
            } else if (isRightDiePseudoPalindrome(inputLine)) {
                sb.append("1").append("\n");
            } else {
                sb.append("2").append("\n");
            }
        }


        System.out.println(sb);

    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isLeftDiePseudoPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        boolean chance = true;

        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {

                if (chance) {
                    chance = false;
                    left++;

                } else {
                    return false;
                }


            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    public static boolean isRightDiePseudoPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        boolean chance = true;

        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {

                if (chance) {
                    chance = false;
                    right--;

                } else {
                    return false;
                }


            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
