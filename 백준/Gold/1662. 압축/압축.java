import java.io.*;
import java.util.*;

class Data {
    int length;
    int lastNumber;

    Data(int length, int lastNumber) {
        this.length = length;
        this.lastNumber = lastNumber;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String S = br.readLine();

        ///////////////////////////////////////////////////////////////////////////

        Stack<Data> stack = new Stack<>();
        String tmp = "";

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                stack.push(new Data(tmp.length(), tmp.charAt(tmp.length() - 1) - '0'));
                stack.push(null);
                tmp = "";
            } else if (S.charAt(i) == ')') {

                Data cur = null;

                // 닫는 괄호가 바로 온 경우
                if (tmp.equals("")) {

                    // 여는 괄호가 바로 있네
                    if (stack.peek() == null) {
                        cur = new Data(0, -1);
                    } else {
                        cur = stack.pop();
                    }

                } else {
                    if (stack.peek() == null) {
                        cur = new Data(tmp.length(), tmp.charAt(tmp.length() - 1) - '0');
                    } else {
                        cur = new Data(stack.peek().length + tmp.length(), tmp.charAt(tmp.length() - 1) - '0');
                        stack.pop();
                    }

                }

                // 여는 괄호 제거
                stack.pop();

                // 전 Data
                Data prev = stack.pop();

                Data newData = new Data(cur.length * prev.lastNumber + prev.length - 1, cur.lastNumber);

                if (!stack.isEmpty() && stack.peek() != null) {
                    newData.length += stack.peek().length;
                    stack.pop();
                }

                stack.push(newData);
                tmp = "";


            } else if (S.charAt(i) >= '0' && S.charAt(i) <= '9') {
                tmp += S.charAt(i);
            }

        }


        if (!tmp.equals("")) {
            if (stack.isEmpty()) {
                stack.push(new Data(tmp.length(), tmp.charAt(tmp.length() - 1) - '0'));
            } else {
                Data prev = stack.pop();
                stack.push(new Data(tmp.length() + prev.length, tmp.charAt(tmp.length() - 1) - '0'));
            }
        }

        System.out.println(stack.pop().length);
    }
}