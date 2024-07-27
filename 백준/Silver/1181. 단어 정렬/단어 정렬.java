import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        HashMap<String,Boolean> hashMap = new HashMap<>();
        for(int i = 0 ; i < N ; i++){
            hashMap.put(br.readLine(),true);
        }

        ArrayList<String> arrayList = new ArrayList<>();

        for(String key: hashMap.keySet())
            arrayList.add(key);

        Collections.sort(arrayList,new Comparator<String>(){
            @Override
            public int compare(String o1,String o2){
                if(o1.length() < o2.length())
                    return -1;
                else if (o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }else
                    return 1;
            }
        });

        for(String value: arrayList)
             sb.append(value).append("\n");
        System.out.println(sb);

    }
}
