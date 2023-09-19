import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n, m;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		st = new StringTokenizer(br.readLine());
		int number = Integer.parseInt(st.nextToken());
		for (int i = 0; i < number; i++) {
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			arr[y][x] = 1;
		}

		st = new StringTokenizer(br.readLine());
		number = Integer.parseInt(st.nextToken());
		for (int i = 0; i < number; i++) {
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			arr[y][x] = 2;
		}

		st = new StringTokenizer(br.readLine());
		number = Integer.parseInt(st.nextToken());
		for (int i = 0; i < number; i++) {
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			arr[y][x] = 3;
		}

		//////////////////////////////////////////////////////

		boolean[][] safe_areas = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				safe_areas[i][j] = true;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 퀸
				if (arr[i][j] == 1) {
					safe_areas[i][j] = false;
					int y, x;

					// 위로
					y = i;
					x = j;
					while (true) {
						y--;
						if (y < 0)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}

					// 아래로
					y = i;
					x = j;
					while (true) {
						y++;
						if (y >= n)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}

					// 왼쪽
					y = i;
					x = j;
					while (true) {
						x--;
						if (x < 0)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}

					// 왼쪽
					y = i;
					x = j;
					while (true) {
						x++;
						if (x >= m)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}

					// 왼쪽 아래로
					y = i;
					x = j;
					while (true) {
						y++;
						x--;
						if (y >= n || x < 0)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}
					// 오른쪽 아래로
					y = i;
					x = j;
					while (true) {
						y++;
						x++;
						if (y >= n || x >= m)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}

					// 오른쪽 위로
					y = i;
					x = j;
					while (true) {
						y--;
						x++;
						if (y < 0 || x >= m)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}
					// 왼쪽 위로
					y = i;
					x = j;
					while (true) {
						y--;
						x--;
						if (x < 0 || y < 0)
							break;
						if (arr[y][x] == 0) {
							safe_areas[y][x] = false;
						} else {
							break;
						}
					}
				}
				// 나이트
				else if (arr[i][j] == 2) {

					safe_areas[i][j] = false;
					// 1
					if (i - 1 >= 0 && j - 2 >= 0)
						safe_areas[i - 1][j - 2] = false;
					// 2
					if (i - 2 >= 0 && j - 1 >= 0)
						safe_areas[i - 2][j - 1] = false;
					// 3
					if (i - 2 >= 0 && j + 1 < m)
						safe_areas[i - 2][j + 1] = false;
					// 4
					if (i - 1 >= 0 && j + 2 < m)
						safe_areas[i - 1][j + 2] = false;
					// 5
					if (i + 1 < n && j + 2 < m)
						safe_areas[i + 1][j + 2] = false;
					// 6
					if (i + 2 < n && j + 1 < m)
						safe_areas[i + 2][j + 1] = false;
					// 7
					if (i + 2 < n && j - 1 >= 0)
						safe_areas[i + 2][j - 1] = false;
					// 8
					if (i + 1 < n && j - 2 >= 0)
						safe_areas[i + 1][j - 2] = false;
				}
				// 폰
				else if (arr[i][j] == 3) {
					safe_areas[i][j] = false;
				} else {

				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(safe_areas[i][j] + " ");
//			}
//			System.out.println();
//		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (safe_areas[i][j])
					count++;
			}
		}
		System.out.println(count);

	}

}
