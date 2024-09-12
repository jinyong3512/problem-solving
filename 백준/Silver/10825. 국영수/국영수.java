import java.io.*;
import java.util.*;

class Person {
    String name;
    int score1;
    int score2;
    int score3;

    Person(String name, int score1, int score2, int score3) {
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Person[] persons = new Person[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            persons[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        ////////////////////////////////////////////

        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
               if(o1.score1 < o2.score1)
                   return 1;
               else if (o1.score1 > o2.score1)
                   return -1;
               else{
                   if(o1.score2 < o2.score2)
                       return -1;
                   else if (o1.score2 > o2.score2)
                       return 1;
                   else{
                       if(o1.score3 < o2.score3)
                           return 1;
                       else if ( o1.score3 > o2.score3)
                           return -1;
                       else{
                           return o1.name.compareTo(o2.name);
                       }
                   }
               }
            }
        });

        for(int i =0 ; i < N ; i++){
            sb.append(persons[i].name).append("\n");
        }
        System.out.println(sb);


    }
}