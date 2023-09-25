import java.io.*;
import java.util.*;

public class Main {

	public static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/////////////////////////////////////////////////////

		recursion(arr, 0, 0, 0);

		System.out.println(answer);

	}

	public static void recursion(int[][] arr, int r, int c, int depth) {

		if (depth == 3) {

			int[][] new_arr = new int[arr.length][arr[0].length];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					new_arr[i][j] = arr[i][j];
				}
			}

			boolean[][] visited = new boolean[arr.length][arr[0].length];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (new_arr[i][j] == 2) {
						dfs(new_arr, visited, i, j);
					}
				}
			}

			int answer_candidate = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (!visited[i][j])
						answer_candidate++;
				}
			}

			int wall_count = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (new_arr[i][j]==1)
						wall_count++;
				}
			}

			answer = Math.max(answer, answer_candidate-wall_count);

//			if (answer < answer_candidate) {
//
//				for (int i = 0; i < new_arr.length; i++) {
//					for (int j = 0; j < new_arr[0].length; j++) {
//						System.out.print(new_arr[i][j] + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println("========" + answer_candidate + "=========");
//
//				answer = answer_candidate;
//			}

			return;
		}

		for (int i = r; i < arr.length; i++) {
			for (int j = c; j < arr[0].length; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					recursion(arr, r, c, depth + 1);
					arr[i][j] = 0;
				}
			}
		}

	}

	public static void dfs(int[][] arr, boolean[][] visited, int i, int j) {
		visited[i][j] = true;

		// 위로
		if (i - 1 >= 0 && arr[i - 1][j] == 0 && !visited[i - 1][j]) {
			dfs(arr, visited, i - 1, j);
		}

		// 아래로
		if (i + 1 < arr.length && arr[i + 1][j] == 0 && !visited[i + 1][j]) {
			dfs(arr, visited, i + 1, j);
		}

		// 왼쪽
		if (j - 1 >= 0 && arr[i][j - 1] == 0 && !visited[i][j - 1]) {
			dfs(arr, visited, i, j - 1);
		}

		// 오른쪽
		if (j + 1 < arr[0].length && arr[i][j + 1] == 0 && !visited[i][j + 1])
			dfs(arr, visited, i, j + 1);
	}
}
