import java.io.*;
import java.util.*;

public class Main {

	public static int answer;
	public static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int M, N, K;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = M - Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = M - Integer.parseInt(st.nextToken());

			for (int y = y2; y < y1; y++) {
				for (int x = x1; x < x2; x++) {
					arr[y][x] = 1;
				}
			}
		}

		boolean[][] visited = new boolean[M][N];

		answer = 0;
		ArrayList<Integer> arrayList = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && arr[i][j] == 0) {
					count = 0;
					dfs(arr, i, j, visited);
					answer++;
					arrayList.add(count);
					
				}
			}
		}
		
		Collections.sort(arrayList);

		System.out.println(answer);
		for(int tmp : arrayList)
			System.out.print(tmp+" ");
		

	}

	public static void dfs(int[][] arr, int i, int j, boolean[][] visited) {
		count++;
		visited[i][j] = true;

		if (i - 1 >= 0 && !visited[i - 1][j] && arr[i - 1][j] == 0)
			dfs(arr, i - 1, j, visited);

		if (i + 1 < arr.length && !visited[i + 1][j] && arr[i + 1][j] == 0)
			dfs(arr, i + 1, j, visited);

		if (j - 1 >= 0 && !visited[i][j - 1] && arr[i][j - 1] == 0)
			dfs(arr, i, j - 1, visited);

		if (j + 1 < arr[0].length && !visited[i][j + 1] && arr[i][j + 1] == 0)
			dfs(arr, i, j + 1, visited);

	}
}