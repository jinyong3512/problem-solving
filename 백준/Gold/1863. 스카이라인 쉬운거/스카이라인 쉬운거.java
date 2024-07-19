import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        ///////////////////////////////////////////////////

        Stack<Integer> stack = new Stack<>();
        int answer = 0;

        for (int i = 0; i < n; i++) {

            int curX = points[i][0];
            int curY = points[i][1];

            if (curY == 0) {
                stack.clear();
                continue;
            }

            if (stack.isEmpty()) {
                stack.push(curY);
                answer++;
                continue;
            }

            if (stack.peek() < curY) {
                stack.push(curY);
                answer++;
            } else if (stack.peek() == curY) {
                // 일어 날 수가 없는 일이다.
            } else {
                while (true) {
                    if(stack.isEmpty()){
                        stack.push(curY);
                        answer++;
                        break;
                    }
                    if(stack.peek() < curY){
                        stack.push(curY);
                        answer++;
                        break;
                    }
                    else if ( stack.peek() == curY){
                        break;
                    }else{
                        stack.pop();
                    }
                }
            }

        }

        System.out.println(answer);

    }
}
