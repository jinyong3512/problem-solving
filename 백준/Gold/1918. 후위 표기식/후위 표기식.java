import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 12:34 시작

        // A+B*C-D/E
        // 답 ABC*+DE/-

        // A+BC*-DE/

        // A+B*C/D
        // 답 ABCD/*+
        // 답 ABC*D/+

        String inputString = br.readLine();

        //////////////////////////////////////////////

        // 1. 괄호 풀기
        // 2. 연산자 높은거 풀기
        // 3. 연산자 낮은거 풀기

        Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < inputString.length(); i++) {
            char curChar = inputString.charAt(i);
            if ('A' <= curChar && curChar <= 'Z') {
                String tmp = String.valueOf(curChar);
                if (!deque.isEmpty() && (deque.peekLast().equals("*") || deque.peekLast().equals("/"))) {
                    tmp = tmp + deque.removeLast();
                    tmp = deque.removeLast() + tmp;
                }
                deque.addLast(tmp);
            } else if (curChar == '(') {
                deque.addLast(String.valueOf(curChar));
            } else if (curChar == ')') {
                Deque<String> newDeque = new ArrayDeque<>();
                while (!deque.isEmpty() && !deque.peekLast().equals("(")) {
                    newDeque.addFirst(deque.removeLast());
                }
                deque.removeLast();

                while(newDeque.size()!=1){
                    String prev = newDeque.removeFirst();
                    String sign = newDeque.removeFirst();
                    String next = newDeque.removeFirst();

                    String tmp = prev+next+sign;

                    newDeque.addFirst(tmp);
                }
                String tmp = newDeque.removeFirst();

                if (!deque.isEmpty() && (deque.peekLast().equals("*") || deque.peekLast().equals("/"))) {
                    tmp = tmp + deque.removeLast();
                    tmp = deque.removeLast() + tmp;
                }
                deque.addLast(tmp);
            } else if (curChar == '+' || curChar == '-') {
                deque.addLast(String.valueOf(curChar));
            } else if (curChar == '*' || curChar == '/') {
                deque.addLast(String.valueOf(curChar));
            } else {
                throw new IOException();
            }
        }

//        for (String value : deque) {
//            System.out.print(value + " ");
//        }

        while(deque.size()!=1){
            String prev = deque.removeFirst();
            String sign = deque.removeFirst();
            String next = deque.removeFirst();

            String tmp = prev+next+sign;

            deque.addFirst(tmp);
        }

        for(String value: deque)
            System.out.println(value);


    }
}