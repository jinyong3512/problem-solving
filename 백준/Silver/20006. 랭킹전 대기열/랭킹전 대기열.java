import java.io.*;
import java.util.*;

class Person {
    int l;
    String n;

    Person(int l, String n) {
        this.l = l;
        this.n = n;
    }
}

class Data {
    int minL;
    int maxL;
    ArrayList<Person> persons;
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int p, m;

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Data> datas = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int curL = Integer.parseInt(st.nextToken());
            String curN = st.nextToken();

            boolean find = false;
            for (int j = 0; j < datas.size(); j++) {
                if (datas.get(j).minL <= curL && curL <= datas.get(j).maxL) {
                    if (datas.get(j).persons.size() < m) {
                        find = true;

                        datas.get(j).persons.add(new Person(curL, curN));

                        break;
                    }
                }

            }

            if (!find) {
                Data newData = new Data();
                newData.persons = new ArrayList<>();
                newData.persons.add(new Person(curL,curN));
                newData.minL = curL - 10;
                newData.maxL = curL + 10;
                datas.add(newData);
            }
        }

        for(int i =0 ; i < datas.size(); i++){
            Collections.sort(datas.get(i).persons,new Comparator<Person>(){
                @Override
                public int compare(Person o1, Person o2){
                    return o1.n.compareTo(o2.n);
                }
            });

            if(datas.get(i).persons.size() == m){
                sb.append("Started!").append("\n");
            }else{
                sb.append("Waiting!").append("\n");
            }

            for(int j = 0 ; j < datas.get(i).persons.size(); j++){
                sb.append(datas.get(i).persons.get(j).l).append(" ").append(datas.get(i).persons.get(j).n).append("\n");
            }
        }
        System.out.println(sb);
    }
}