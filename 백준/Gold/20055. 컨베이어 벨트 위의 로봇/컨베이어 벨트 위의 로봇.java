import java.io.*;
import java.util.*;

class Data {
    int naegudo;
    boolean robot;

    Data(int naegudo, boolean robot) {
        this.naegudo = naegudo;
        this.robot = robot;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 23:43

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Data> arrayList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++)
            arrayList.add(new Data(Integer.parseInt(st.nextToken()), false));

        //////////////////////////////////////////////

        int answer = 0;
        while (true) {
            answer++;
            function1(N, arrayList);
            function2(N, arrayList);
            function3(N, arrayList);
            if (function4(N, arrayList) >= K) {
                System.out.println(answer);
                break;
            }
        }

    }

    public static void function1(int N, ArrayList<Data> arrayList) {
        arrayList.add(0, arrayList.remove(arrayList.size() - 1));
        arrayList.get(N - 1).robot = false;
    }

    public static void function2(int N, ArrayList<Data> arrayList) {
        for (int i = N - 2; i >= 0; i--) {
            if (arrayList.get(i + 1).naegudo > 0 && arrayList.get(i).robot && !arrayList.get(i + 1).robot) {
                arrayList.get(i).robot = false;
                arrayList.get(i + 1).robot = true;
                arrayList.get(i + 1).naegudo--;
            }
        }

        arrayList.get(N - 1).robot = false;
    }

    public static void function3(int N, ArrayList<Data> arrayList) {
        if (arrayList.get(0).naegudo > 0) {
            arrayList.get(0).robot = true;
            arrayList.get(0).naegudo--;
        }
    }

    public static int function4(int N, ArrayList<Data> arrayList) {
        int k = 0;
        for(int i =0 ; i< arrayList.size(); i++){
            if(arrayList.get(i).naegudo == 0)
                k++;
        }
        return k;
    }
}