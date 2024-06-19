import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> tmpArrayList = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String tmpStr = br.readLine();
            arrayList.add(tmpStr);
            tmpArrayList.add(tmpStr);
        }

        /////////////////////////////////////////////////

        HashMap<String, Integer> hashMap = new HashMap<>();

        Collections.sort(tmpArrayList);

        int maxM = -1;
        for (int i = 0; i < tmpArrayList.size() - 1; i++) {

            String curStr = tmpArrayList.get(i);
            String nextStr = tmpArrayList.get(i + 1);

            int curM = 0;
            for (int j = 0; j < curStr.length() && j < nextStr.length(); j++) {
                if (curStr.charAt(j) == nextStr.charAt(j)) {
                    curM++;
                } else {
                    break;
                }
            }

            if (maxM > curM) {
                continue;
            } else if (maxM == curM) {
                hashMap.put(curStr.substring(0, maxM), 0);
            } else {
                maxM = curM;
                hashMap.clear();
                hashMap.put(curStr.substring(0, maxM), 0);
            }
        }

        String S = "";
        String T = "";

        for (int i = 0; i < arrayList.size(); i++) {

            if( arrayList.get(i).length() < maxM)
                continue;

            if (hashMap.containsKey(arrayList.get(i).substring(0, maxM))) {
                if(S.isEmpty()){
                    hashMap.clear();
                    hashMap.put(arrayList.get(i).substring(0, maxM), 0);
                    S = arrayList.get(i);
                }else{
                    T = arrayList.get(i);
                    break;
                }
            }
        }
        System.out.println(S+"\n"+T);

    }
}