import java.io.*;
import java.util.*;

public class Main {

	public static int answer = 0;

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

		////////////////////////////////////////
		recursion(arr, 0, 0);

		System.out.println(answer);
	}

	public static void recursion(int[][] arr, int depth, int direction) {

		if (depth == 6) {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					max = Math.max(max, arr[i][j]);
				}
			}
			answer = Math.max(answer, max);
			return;
		}

		// 복사하기
		int[][] new_arr = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				new_arr[i][j] = arr[i][j];
			}
		}

		// 위로
		if (direction == 1) {

			// 모으기
			for (int j = 0; j < new_arr[0].length; j++) {
				for (int i = 0; i < new_arr.length; i++) {
					if (new_arr[i][j] != 0) {
						for (int y = 0; y < i; y++) {
							if (new_arr[y][j] == 0) {
								int tmp = new_arr[i][j];
								new_arr[i][j] = new_arr[y][j];
								new_arr[y][j] = tmp;
								break;
							}
						}
					}
				}
			}

			// 합치기
			for (int j = 0; j < new_arr[0].length; j++) {
				for (int i = 0; i < new_arr.length - 1; i++) {
					if (new_arr[i][j] == new_arr[i + 1][j]) {
						new_arr[i][j] *= 2;

						for (int y = i + 2; y < new_arr.length; y++) {
							new_arr[y - 1][j] = new_arr[y][j];
						}
						new_arr[new_arr.length - 1][j] = 0;

					}

				}
			}

		}
		// 오른쪽
		else if (direction == 2) {

			// 모으기
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = new_arr[0].length - 1; j >= 0; j--) {
					if (new_arr[i][j] != 0) {
						for (int x = new_arr[0].length - 1; x > j; x--) {
							if (new_arr[i][x] == 0) {
								int tmp = new_arr[i][x];
								new_arr[i][x] = new_arr[i][j];
								new_arr[i][j] = tmp;
								break;
							}
						}
					}
				}
			}

			// 합치기
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = new_arr[0].length - 1; j > 0; j--) {
					if (new_arr[i][j] == new_arr[i][j - 1]) {
						new_arr[i][j] *= 2;

						for (int x = j - 2; x >= 0; x--) {
							new_arr[i][x + 1] = new_arr[i][x];
						}

						new_arr[i][0] = 0;
					}
				}
			}

		}
		// 아래
		else if (direction == 3) {
			// 모으기
			for (int j = 0; j < new_arr[0].length; j++) {
				for (int i = new_arr.length - 1; i >= 0; i--) {
					if (new_arr[i][j] != 0) {
						for (int y = new_arr.length - 1; y >= i + 1; y--) {
							if (new_arr[y][j] == 0) {
								int tmp = new_arr[i][j];
								new_arr[i][j] = new_arr[y][j];
								new_arr[y][j] = tmp;
								break;
							}
						}
					}
				}
			}

			// 합치기
			for (int j = 0; j < new_arr[0].length; j++) {
				for (int i = new_arr.length - 1; i > 0; i--) {
					if (new_arr[i][j] == new_arr[i - 1][j]) {
						new_arr[i][j] *= 2;

						for (int y = i - 2; y >= 0; y--) {
							new_arr[y + 1][j] = new_arr[y][j];
						}
						new_arr[0][j] = 0;

					}

				}
			}
		}
		// 왼쪽
		else if (direction == 4) {
			// 모으기
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = 0; j < new_arr[0].length; j++) {
					if (new_arr[i][j] != 0) {
						for (int x = 0; x < j; x++) {
							if (new_arr[i][x] == 0) {
								int tmp = new_arr[i][x];
								new_arr[i][x] = new_arr[i][j];
								new_arr[i][j] = tmp;
								break;
							}
						}
					}
				}
			}

			// 합치기
			for (int i = 0; i < new_arr.length; i++) {
				for (int j = 0; j < new_arr[0].length - 1; j++) {
					if (new_arr[i][j] == new_arr[i][j + 1]) {
						new_arr[i][j] *= 2;

						for (int x = j + 2; x < new_arr[0].length; x++) {
							new_arr[i][x - 1] = new_arr[i][x];
						}

						new_arr[i][new_arr[0].length - 1] = 0;
					}
				}
			}
		}

//		if (depth == 1) {
//			System.out.println("direction = " + direction);
//			for (int i = 0; i < arr.length; i++) {
//				for (int j = 0; j < arr[0].length; j++) {
//					System.out.print(new_arr[i][j] + " ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
//			System.out.println("");
//		}

		recursion(new_arr, depth + 1, 1);
		recursion(new_arr, depth + 1, 2);
		recursion(new_arr, depth + 1, 3);
		recursion(new_arr, depth + 1, 4);

	}
}