import java.io.*;
import java.util.*;

class Data {
    String name;
    int maxValue;

    Data(String name, int maxValue) {
        this.maxValue = maxValue;
        this.name = name;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, M;
        Data[] datas;

        // 해당 이하면 걸리는거야

        // 범위를 나타내는 자료구조 뭐가 있을까

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        datas = new Data[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            datas[i] = new Data(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        //////////////////////////////////////////////////////////////////////////

        // lowerBound(하한): 이상이 처음으로 나오는 위치
        // upperBound(상한): 초과가 처음으로 나오는 위치

        for (int t = 0; t < M; t++) {
            int curScore = Integer.parseInt(br.readLine());

            int leftIndex = 0;
            int rightIndex = N - 1;

            while (leftIndex <= rightIndex) {
                int midIndex = (leftIndex + rightIndex) / 2;

                if(curScore < datas[midIndex].maxValue){
                    rightIndex = midIndex-1;
                }
                else if (curScore == datas[midIndex].maxValue){
                    rightIndex = midIndex-1;
                }else{
                    leftIndex = midIndex+1;
                }

            }

            sb.append(datas[leftIndex].name).append("\n");

        }
        System.out.println(sb);


    }


}