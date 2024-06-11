import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int M;
        boolean[][] graph;
        int[] course;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (st.nextToken().equals("1"))
                    graph[i][j] = true;
            }
        }

        course = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i =0 ; i < M ; i++){
            course[i] = Integer.parseInt(st.nextToken());
        }

        //////////////////////////////////////////////////////////////////////

        int[] groupNumbers = new int[N + 1];
        int groupNumber = 0;

        for (int i = 1; i <= N; i++) {
            if (groupNumbers[i] != 0)
                continue;

            groupNumber++;

            dfs(graph, groupNumbers, groupNumber,i);
        }

        boolean can = true;
        for(int i = 0 ; i < M-1 ; i++){
            int curPoint = course[i];
            int nextPoint = course[i+1];
            if(groupNumbers[curPoint] != groupNumbers[nextPoint])
                can = false;
        }

        if(can)
            System.out.println("YES");
        else
            System.out.println("NO");


    }

    public static void dfs(boolean[][] graph, int[] groupNumbers, int groupNumber,int cur) {

        groupNumbers[cur] = groupNumber;

        for(int j = 1 ; j < graph.length ; j++){
            if(!graph[cur][j])
                continue;

            if(groupNumbers[j]!=0)
                continue;

            dfs(graph,groupNumbers,groupNumber,j);
        }


    }
}