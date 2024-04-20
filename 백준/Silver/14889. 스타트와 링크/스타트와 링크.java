import java.io.*;
import java.util.*;

public class Main {

    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N;
        int[][] arr;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /////////////////////////////////////////////////////

        answer = Integer.MAX_VALUE;
        boolean[] visited = new boolean[N];
        combination(arr,-1, visited, 0);
        System.out.println(answer);

    }

    public static void combination(int[][]arr, int index, boolean[] visited, int depth) {

        if (depth == visited.length / 2) {

            ArrayList<Integer> aTeam = new ArrayList<>();
            ArrayList<Integer> bTeam = new ArrayList<>();

            for(int i =0 ; i < visited.length ; i++){
                if(visited[i])
                    aTeam.add(i);
                else
                    bTeam.add(i);
            }

            int aTeamPower = 0;
            int bTeamPower = 0;

            for(int i =0 ; i < aTeam.size();i++){
                for(int j = i+1 ; j < aTeam.size();j++){
                    aTeamPower += arr[aTeam.get(i)][aTeam.get(j)];
                    aTeamPower += arr[aTeam.get(j)][aTeam.get(i)];
                }
            }
            for(int i =0 ; i < bTeam.size();i++){
                for(int j = i+1 ; j < bTeam.size();j++){
                    bTeamPower += arr[bTeam.get(i)][bTeam.get(j)];
                    bTeamPower += arr[bTeam.get(j)][bTeam.get(i)];
                }
            }

            answer= Math.min(answer,Math.abs(aTeamPower-bTeamPower));


            return;
        }

        for (int i = index + 1; i < visited.length; i++) {
            visited[i] = true;
            combination(arr,i, visited, depth + 1);
            visited[i] = false;
        }

    }
}
