import java.io.*;
import java.util.*;

class Main {

	// 상 우상 우 우하 하 하좌 좌 좌상
	public static int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dx = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static boolean can;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int M, N;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/////////////////////////////////////////////////////////
		int answer = 0;

		boolean[][] visited = new boolean[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (!visited[i][j]) {
					can = true;
					visited[i][j] = true;
					dfs(arr, visited, i, j);

					if (can) {
//						System.out.println(i + " " + j);
						answer++;
					}
				}
			}
		}

		System.out.println(answer);

	}

	public static void dfs(int[][] arr, boolean[][] visited, int i, int j) {
		for (int d = 0; d < 8; d++) {
			int new_i = i + dy[d];
			int new_j = j + dx[d];

			if (new_i >= 0 && new_j >= 0 && new_i < arr.length && new_j < arr[0].length) {

				if (arr[new_i][new_j] > arr[i][j]) {
					can = false;
				} else if (arr[new_i][new_j] == arr[i][j] && !visited[new_i][new_j]) {
					visited[new_i][new_j] = true;
					dfs(arr, visited, new_i, new_j);
				}

			}
		}
	}
}