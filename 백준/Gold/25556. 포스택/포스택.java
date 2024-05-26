import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 길이가 N인 순열 A
        // 4개의 비어있는 스택

        // 1~N개가 무작위로 들어 있다
        // 오름차순으로 예쁘게 만들기

        // 1. 4개의 스택중 하나에 삽입
        // 2. 4개의 스택에서 수를 모두 꺼낸다
        // 3. 가장 나중에 꺼낸 수 가 맨 앞

        // N은 10^5이다

        int N;
        String inputLine;

        N = Integer.parseInt(br.readLine());
        inputLine = br.readLine();

        ///////////////////////////////////////////////////

        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        for(int i =0 ; i < 4; i ++){
            stacks.add(new Stack<>());
        }

        st = new StringTokenizer(inputLine);

        for(int i =0 ; i < N ; i++){
            int curNumber = Integer.parseInt(st.nextToken());

            boolean can = false;
            for(int j =0 ; j < stacks.size(); j++){
                if(stacks.get(j).isEmpty()){
                    can=true;
                    stacks.get(j).add(curNumber);
                    break;
                }

                if(stacks.get(j).peek() < curNumber){
                    can=true;
                    stacks.get(j).add(curNumber);
                    break;
                }

            }

            if(!can){
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");



    }
}
