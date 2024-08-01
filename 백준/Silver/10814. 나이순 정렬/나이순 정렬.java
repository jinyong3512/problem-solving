import java.io.*;
import java.util.*;

class Data {
    int age;
    String name;
    int initIndex;

    Data(int age, String name, int initIndex) {
        this.age = age;
        this.name = name;
        this.initIndex = initIndex;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Data[] datas = new Data[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            datas[i] = new Data(age, name, i);
        }

        Arrays.sort(datas, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.age < o2.age)
                    return -1;
                else if (o1.age == o2.age)
                    return o1.initIndex - o2.initIndex;
                else
                    return 1;
            }
        });

        for(int i =0 ; i < N ; i++){
            sb.append(datas[i].age).append(" ").append(datas[i].name).append("\n");
        }
        System.out.println(sb);


    }
}
