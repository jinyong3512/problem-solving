import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<String> stack = new Stack<>();
        String tmp;
        boolean input_correct = true;

        String input = br.readLine();

        // 유효성 검사
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '[')
                stack.add(String.valueOf(input.charAt(i)));
            else if(stack.isEmpty())
                input_correct=false;
            else if (input.charAt(i) == ')' && !stack.pop().equals("("))
                input_correct = false;
            else if (input.charAt(i) == ']' && !stack.pop().equals("["))
                input_correct = false;
        }

        if(!input_correct || !stack.isEmpty()){
            sb.append(0);
            System.out.println(sb);
            System.exit(0);
        }


        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '[')
                stack.add(String.valueOf(input.charAt(i)));
            else {
                tmp = stack.pop();
                if (input.charAt(i) == ')') {
                    if (tmp.equals("("))
                        stack.push("2");
                    else {
                        stack.pop();
                        stack.push(String.valueOf(Integer.parseInt(tmp) * 2));
                    }

                } else {
                    if (tmp.equals("["))
                        stack.push("3");
                    else {
                        stack.pop();
                        stack.push(String.valueOf(Integer.parseInt(tmp) * 3));
                    }
                }

                tmp = stack.pop();
                if (!stack.isEmpty() && !stack.peek().equals("(") && !stack.peek().equals("["))
                    stack.push(String.valueOf(Integer.parseInt(stack.pop()) + Integer.parseInt(tmp)));
                else
                    stack.push(tmp);

            }
        }

        sb.append(stack.pop());
        System.out.println(sb);

    }
}