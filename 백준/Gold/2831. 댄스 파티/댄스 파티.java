import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 11:39

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> boysWantHigher = new ArrayList<>();
        ArrayList<Integer> boysWantLower = new ArrayList<>();
        ArrayList<Integer> girlsWantHigher = new ArrayList<>();
        ArrayList<Integer> girlsWantLower = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int curHeight = Integer.parseInt(st.nextToken());
            if (curHeight < 0)
                boysWantLower.add(Math.abs(curHeight));
            else
                boysWantHigher.add(curHeight);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int curHeight = Integer.parseInt(st.nextToken());
            if (curHeight < 0)
                girlsWantLower.add(Math.abs(curHeight));
            else
                girlsWantHigher.add(curHeight);
        }

        ////////////////////////////////////////////////////////

        // 남자 기준으로 큰애랑 원하면 작은걸 원하는 여자들 중 초과 값
        // 남자 기준으로 작은애랑 원하면 큰걸 원하는 여자들 중 최소 값

        Collections.sort(boysWantHigher);
        Collections.sort(boysWantLower);
        Collections.sort(girlsWantHigher);
        Collections.sort(girlsWantLower);

        int answer = 0;

        int index1 = 0;
        int index2 = 0;
        while(index1 < boysWantHigher.size() && index2 < girlsWantLower.size()){
            if(boysWantHigher.get(index1) < girlsWantLower.get(index2)){
                answer++;
                index1++;
                index2++;
            }else if (boysWantHigher.get(index1) == girlsWantLower.get(index2) ){
                index2++;
            }else{
                index2++;
            }
        }

        index1 = 0;
        index2 = 0;
        while(index1 < boysWantLower.size() && index2 < girlsWantHigher.size()){
            if(boysWantLower.get(index1) > girlsWantHigher.get(index2)){
                answer++;
                index1++;
                index2++;
            }else if (boysWantLower.get(index1) == girlsWantHigher.get(index2) ){
                index1++;
            }else{
                index1++;
            }
        }

        System.out.println(answer);

    }
}