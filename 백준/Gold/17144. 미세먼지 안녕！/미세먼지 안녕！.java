import java.io.*;
import java.util.*;

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int R, C, T;
		int[][] arr;

		///////////////////////////////////////////////////////////

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

		/////////////////////////////////////////////////////////////

		Point head = null;
		Point tail = null;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == -1 && head == null) {
					head = new Point(i, j);
				} else if (arr[i][j] == -1 && head != null) {
					tail = new Point(i, j);
				}
			}
		}

		for (int time = 0; time < T; time++) {
			arr = spread(arr);

//			for (int i = 0; i < arr.length; i++) {
//				for (int j = 0; j < arr[0].length; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println("");
//			}

			clean(arr, head, tail);

//			for (int i = 0; i < arr.length; i++) {
//				for (int j = 0; j < arr[0].length; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println("");
//			}

		}

		long answer = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				answer += arr[i][j];
			}

		}
		System.out.println(answer + 2);

	}

	public static int[][] spread(int[][] arr) {
		int[][] new_arr = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == -1) {
					new_arr[i][j] = -1;
				} else if (arr[i][j] == 0) {

				} else {

					int count_can_dust_spread = 0;
					// 위로
					if (i - 1 >= 0 && arr[i - 1][j] != -1) {
						new_arr[i - 1][j] += arr[i][j] / 5;
						count_can_dust_spread++;
					}

					// 아래로
					if (i + 1 < arr.length && arr[i + 1][j] != -1) {
						new_arr[i + 1][j] += arr[i][j] / 5;
						count_can_dust_spread++;
					}

					// 왼쪽
					if (j - 1 >= 0 && arr[i][j - 1] != -1) {
						new_arr[i][j - 1] += arr[i][j] / 5;
						count_can_dust_spread++;
					}

					// 오른쪽
					if (j + 1 < arr[0].length && arr[i][j + 1] != -1) {
						new_arr[i][j + 1] += arr[i][j] / 5;
						count_can_dust_spread++;
					}

					new_arr[i][j] += arr[i][j] - (arr[i][j] / 5) * count_can_dust_spread;
				}
			}
		}

		return new_arr;

	}

	public static void clean(int[][] arr, Point head, Point tail) {
		int remember_left_top = arr[0][0];

		// 상단
		for (int x = 0; x < arr[0].length - 1; x++) {
			arr[0][x] = arr[0][x + 1];
		}

		// 우측
		for (int y = 0; y < head.y; y++) {
			arr[y][arr[0].length - 1] = arr[y + 1][arr[0].length - 1];
		}

		// 하단
		for (int x = arr[0].length - 1; x > 0; x--) {
			arr[head.y][x] = arr[head.y][x - 1];
		}

		// 좌측
		for (int y = head.y; y > 0; y--) {
			arr[y][0] = arr[y - 1][0];
		}

		arr[1][0] = remember_left_top;

		arr[head.y][head.x] = -1;
		arr[head.y][head.x + 1] = 0;

		///////////////////////////////////////////////////////////

		int remember_right_top = arr[tail.y][arr[0].length - 1];

		// 상단
		for (int x = arr[0].length - 1; x > 0; x--) {
			arr[tail.y][x] = arr[tail.y][x - 1];
		}

		// 좌측
		for (int y = tail.y; y < arr.length - 1; y++) {
			arr[y][0] = arr[y + 1][0];
		}

		// 하단
		for (int x = 0; x < arr[0].length - 1; x++) {
			arr[arr.length - 1][x] = arr[arr.length - 1][x + 1];
		}

		// 우측
		for (int y = arr.length - 1; y > tail.y; y--) {
			arr[y][arr[0].length - 1] = arr[y - 1][arr[0].length - 1];
		}

		arr[tail.y + 1][arr[0].length - 1] = remember_right_top;

		arr[tail.y][tail.x] = -1;
		arr[tail.y][tail.x + 1] = 0;
	}
}