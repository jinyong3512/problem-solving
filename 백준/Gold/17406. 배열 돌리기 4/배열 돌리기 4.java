import java.io.*;
import java.util.*;

class Main {

	public static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M, K;
		int[][] arr;
		int[][] command;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		command = new int[K][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			command[i][0] = Integer.parseInt(st.nextToken());
			command[i][1] = Integer.parseInt(st.nextToken());
			command[i][2] = Integer.parseInt(st.nextToken());
		}
		/////////////////////////////////////////////////////

		answer = Integer.MAX_VALUE;

		boolean[] visited = new boolean[K];
		recursion(arr, command, 0, visited);
		
		System.out.println(answer);
	}

	public static void rotate(int[][] arr, int y, int x, int s) {
		for (int depth = 1; depth <= s; depth++) {
			// 오른쪽 위를 기억해!
			int memorize = arr[y - depth][x + depth];

			// 위에 조져 - 오른쪽 위 점 구하고 왼쪽으로 진행
			for (int j = x + depth; j > x - depth; j--)
				arr[y - depth][j] = arr[y - depth][j - 1];

			// 왼쪽 조져 - 왼쪽 위 구하고 아래로 진행
			for (int i = y - depth; i < y + depth; i++)
				arr[i][x - depth] = arr[i + 1][x - depth];

			// 아래 조져 - 왼쪽 아래 구하고 오른쪽으로 진행
			for (int j = x - depth; j < x + depth; j++)
				arr[y + depth][j] = arr[y + depth][j + 1];

			// 오른쪽 조져 - 오른쪽 아래 점 구하고 위로 진행
			for (int i = y + depth; i > y - depth; i--)
				arr[i][x + depth] = arr[i - 1][x + depth];

			arr[y - depth + 1][x + depth] = memorize;
		}
	}

	public static int value_array(int[][] arr) {
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr[0].length; j++) {
				sum += arr[i][j];
			}
			answer = Math.min(answer, sum);
		}
		return answer;
	}

	public static void recursion(int[][] arr, int[][] command, int depth, boolean[] visited) {
		if (depth == visited.length) {

			answer = Math.min(answer, value_array(arr));

			return;
		}

		for (int i = 0; i < command.length; i++) {
			if (!visited[i]) {

				visited[i] = true;

				int[][] new_arr = new int[arr.length][arr[0].length];

				for (int y = 0; y < arr.length; y++) {
					for (int x = 0; x < arr[0].length; x++) {
						new_arr[y][x] = arr[y][x];
					}
				}

				rotate(new_arr, command[i][0] - 1, command[i][1] - 1, command[i][2]);

				recursion(new_arr, command, depth + 1, visited);

				visited[i] = false;
			}
		}

	}

}