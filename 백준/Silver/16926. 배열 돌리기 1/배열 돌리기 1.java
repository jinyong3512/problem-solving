import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M, R;
		int[][] arr;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		/////////////////////////////////////////////////////////
		for (int i = 0; i < R; i++)
			rotate(arr);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println("");
		}

	}

	public static void rotate(int[][] arr) {
		for (int depth = 0; depth < Math.min(arr.length, arr[0].length) / 2; depth++) {
			int memorize = arr[depth][depth];

			// 맨위
			for (int j = 1 + depth; j < arr[0].length - depth; j++) {
				arr[0 + depth][j - 1] = arr[0 + depth][j];
			}

			// 맨오른쪽
			for (int i = 1 + depth; i < arr.length - depth; i++) {
				arr[i - 1][arr[0].length - 1 - depth] = arr[i][arr[0].length - 1 - depth];
			}

			// 맨아래
			for (int j = arr[0].length - 2 - depth; j >= 0 + depth; j--) {
				arr[arr.length - 1 - depth][j + 1] = arr[arr.length - 1 - depth][j];
			}

			// 맨왼쪽
			for (int i = arr.length - 2 - depth; i >= 0 + depth; i--) {
				arr[i + 1][0 + depth] = arr[i][0 + depth];
			}

			arr[depth + 1][depth] = memorize;
		}
	}
}