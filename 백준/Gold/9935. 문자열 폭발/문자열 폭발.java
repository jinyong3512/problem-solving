import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str;
        String bombStr;

        str = br.readLine();
        bombStr = br.readLine();

        //////////////////////////////////////////////////////

        Deque<Character> strDeque = new ArrayDeque<>();
        Deque<Character> tmpDeque = new ArrayDeque<>();
        Deque<Character> answerDeque = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            strDeque.addLast(str.charAt(i));
        }


        while (strDeque.size() >= bombStr.length()) {

            boolean can = true;
            for (int i = 0; i < bombStr.length(); i++) {
                if (strDeque.peekFirst() == bombStr.charAt(i)) {

                } else {
                    can = false;
                }
                tmpDeque.addLast(strDeque.removeFirst());
            }

            if (can) {

                tmpDeque.clear();

                for (int i = 0; i < bombStr.length(); i++) {
                    if(answerDeque.isEmpty()){
                        break;
                    }else{
                        strDeque.addFirst(answerDeque.removeLast());
                    }
                }

            } else {
                answerDeque.addLast(tmpDeque.removeFirst());

                while (!tmpDeque.isEmpty()) {
                    strDeque.addFirst(tmpDeque.removeLast());
                }
            }


        }

        while(!strDeque.isEmpty()){
            answerDeque.addLast(strDeque.removeFirst());
        }

        if(answerDeque.isEmpty()){
            System.out.println("FRULA");
        }else{
            while(!answerDeque.isEmpty())
                sb.append(answerDeque.removeFirst());
            System.out.println(sb);
        }


    }
}
