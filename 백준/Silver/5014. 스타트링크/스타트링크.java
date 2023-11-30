import java.io.*;
import java.util.*;

class Data{
    int floor;
    int depth;

    Data(int floor,int depth){
        this.floor= floor;
        this.depth= depth;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int F, S, G, U, D;

        ////////////////////////////////////////////////////////////////////////

        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ////////////////////////////////////////////////////////////////////////

        Queue<Data> queue = new LinkedList<>();
        boolean[] visited = new boolean[F+1];

        queue.add(new Data(S,0));
        visited[S]=true;

        while(!queue.isEmpty()){
            Data tmp = queue.remove();

            if(tmp.floor==G){
                sb.append(tmp.depth);
                break;
            }

            if( tmp.floor + U <=F && !visited[tmp.floor+U]){
                visited[tmp.floor+U] = true;
                queue.add(new Data(tmp.floor+U,tmp.depth+1));
            }

            if( tmp.floor - D >= 1 && !visited[tmp.floor-D]){
                visited[tmp.floor-D] = true;
                queue.add(new Data(tmp.floor-D,tmp.depth+1));
            }



        }

        if(sb.length()==0)
            sb.append("use the stairs");

        System.out.println(sb);


    }
}