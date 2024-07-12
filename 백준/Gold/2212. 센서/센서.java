import java.io.*;
import java.util.*;

class Data {
    int number;
    boolean tower;

    Data(int number, boolean tower) {
        this.number = number;
        this.tower = tower;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] sensors = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            sensors[i] = Integer.parseInt(st.nextToken());

        //////////////////////////////////////////////////

        Arrays.sort(sensors);

        int[] gaps = new int[N-1];

        for(int i = 0 ; i < sensors.length-1 ; i++){
            gaps[i] = sensors[i+1] - sensors[i];
        }

        Arrays.sort(gaps);

        int answer = 0;

        for(int i = 0 ; i < gaps.length - K + 1; i++){
            answer += gaps[i];
        }

        System.out.println(answer);

    }
}
