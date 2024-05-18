import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Data {
    String str;
    int depth;

    Data(String str, int depth) {
        this.str = str;
        this.depth = depth;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 문자열 길이는 10^3

        String str;

        str = br.readLine();

        //////////////////////////////////////////////////////

        HashMap<String, Boolean> visited = new HashMap<>();
        visited.put(str, true);

        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(str, 0));

        while (!queue.isEmpty()) {
            Data curData = queue.remove();

            String curString = curData.str;
            int curDepth = curData.depth;

            if (isCan(curString)) {
                System.out.println(curDepth);
                break;
            }

            for (int i = 0; i < curString.length(); i++) {
                for (int j = i + 1; j < curString.length(); j++) {
                    if (curString.charAt(i) != curString.charAt(j)) {

                        String newString =
                                curString.substring(0, i)
                                        + curString.charAt(j)
                                        + curString.substring(i + 1, j)
                                        + curString.charAt(i)
                                        + curString.substring(j + 1);

//                        StringBuilder newStr = new StringBuilder(curString);
//                        newStr.setCharAt(i,'a');
//                        newStr.setCharAt(j,'b');
//                        String newString = newStr.toString();

                        if (visited.containsKey(newString))
                            continue;

                        visited.put(newString, true);
                        queue.add(new Data(newString, curDepth + 1));
                    }
                }
            }

        }
    }

    public static boolean isCan(String str) {

        int leftAnotherIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(0)) {
                leftAnotherIndex = i;
                break;
            }
        }

        int rightAnotherIndex = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != str.charAt(0)) {
                rightAnotherIndex = i;
                break;
            }
        }

        for (int i = leftAnotherIndex + 1; i < rightAnotherIndex; i++) {
            if (str.charAt(i) == str.charAt(0))
                return false;
        }

        return true;

    }
}