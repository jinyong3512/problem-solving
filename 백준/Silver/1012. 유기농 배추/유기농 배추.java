import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int M, N, K;
			boolean[][] map;

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new boolean[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				map[y][x] = true;
			}

			///////////////////////////////////////////

			int answer = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j]) {
						dfs(map, i, j);
						answer++;
					}
				}
			}

			System.out.println(answer);

		}

	}

	public static void dfs(boolean[][] map, int i, int j) {
		map[i][j] = false;
		
		// 위로
		if(i-1>=0 && map[i-1][j]) 
			dfs(map,i-1,j);
		
		// 아래로
		if(i+1<map.length && map[i+1][j])
			dfs(map,i+1,j);
		
		// 왼쪽
		if(j-1>=0 && map[i][j-1])
			dfs(map,i,j-1);
		
		//오른쪽
		if( j+1 < map[0].length && map[i][j+1])
			dfs(map,i,j+1);

	}
}
