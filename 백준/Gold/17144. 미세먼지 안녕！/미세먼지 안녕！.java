import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int R, C, T;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/////////////////////////////////////////////////////

		int cleaner_head_i = -1;
		int cleaner_tail_i = -1;

		for (int i = 0; i < R; i++) {
			if (arr[i][0] == -1) {
				cleaner_head_i = i;
				break;
			}
		}
		for (int i = R - 1; i >= 0; i--) {
			if (arr[i][0] == -1) {
				cleaner_tail_i = i;
				break;
			}
		}

		for (int t = 0; t < T; t++) {
			// 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
			int[][] new_arr = new int[R][C];
			spread_of_fine_dust(arr, new_arr);

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					arr[i][j] = new_arr[i][j];
				}
			}

			// 2. 공기청정기가 작동한다.

			// 대가리 왼쪽
			for (int i = cleaner_head_i - 1; i >= 0; i--) {
				arr[i + 1][0] = arr[i][0];
			}

			// 대가리 위
			for (int j = 1; j < arr[0].length; j++) {
				arr[0][j - 1] = arr[0][j];
			}

			// 대가리 오른쪽
			for (int i = 1; i <= cleaner_head_i; i++) {
				arr[i - 1][arr[0].length - 1] = arr[i][arr[0].length - 1];
			}

			// 대가리 아래
			for (int j = arr[0].length - 2; j >= 0; j--) {
				arr[cleaner_head_i][j + 1] = arr[cleaner_head_i][j];
			}

			arr[cleaner_head_i][0] = -1;
			arr[cleaner_head_i][1] = 0;

			// 꼬리 왼쪽
			for (int i = cleaner_tail_i + 1; i < arr.length; i++) {
				arr[i - 1][0] = arr[i][0];
			}

			// 꼬리 아래
			for (int j = 1; j < arr[0].length; j++) {
				arr[arr.length - 1][j - 1] = arr[arr.length - 1][j];
			}

			// 꼬리 오른쪽
			for (int i = arr.length - 1 - 1; i >= cleaner_tail_i; i--) {
				arr[i + 1][arr[0].length - 1] = arr[i][arr[0].length - 1];
			}

			// 꼬리 위
			for (int j = arr[0].length - 1 - 1; j >= 0; j--) {
				arr[cleaner_tail_i][j + 1] = arr[cleaner_tail_i][j];
			}

			arr[cleaner_tail_i][0] = -1;
			arr[cleaner_tail_i][1] = 0;

			

		}

		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] > 0)
					count += arr[i][j];
			}
		}
		System.out.println(count);
	}

	public static void spread_of_fine_dust(int[][] arr, int[][] new_arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] > 0) {
					int count = 0;

					// 위
					if (i - 1 >= 0 && arr[i - 1][j] != -1) {
						count++;
						new_arr[i - 1][j] += arr[i][j] / 5;
					}

					// 아래
					if (i + 1 < arr.length && arr[i + 1][j] != -1) {
						count++;
						new_arr[i + 1][j] += arr[i][j] / 5;
					}

					// 왼쪽
					if (j - 1 >= 0 && arr[i][j - 1] != -1) {
						count++;
						new_arr[i][j - 1] += arr[i][j] / 5;
					}

					// 오른쪽
					if (j + 1 < arr[0].length && arr[i][j + 1] != -1) {
						count++;
						new_arr[i][j + 1] += arr[i][j] / 5;
					}

					new_arr[i][j] += arr[i][j] - (arr[i][j] / 5) * (count);

				}
			}
		}
	}

}