import java.io.*;
import java.util.*;

public class Main {

	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

//		boolean[][] arr = new boolean[N][N];
//		recursion(N, arr, 0);

		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = -1;

		recursion2(N, arr, 0);

		System.out.println(answer);
	}

	public static void recursion(int N, boolean[][] arr, int row) {

		if (row == N) {
			answer++;
			return;
		}

		for (int j = 0; j < N; j++) {

			boolean can = true;

			int y, x;

			// 왼쪽 위로
			y = row;
			x = j;
			while (y >= 0 && x >= 0) {
				if (arr[y][x]) {
					can = false;
					y--;
					x--;
				}

				// 위로
				y = row;
				x = j;
				while (y >= 0) {
					if (arr[y][x])
						can = false;
					y--;
				}

				// 오른쪽 위로
				y = row;
				x = j;
				while (y >= 0 && x < N) {
					if (arr[y][x])
						can = false;
					y--;
					x++;
				}

				if (can) {
					arr[row][j] = true;
					recursion(N, arr, row + 1);
					arr[row][j] = false;
				}

			}
		}
	}

	public static void recursion2(int N, int[] arr, int row) {
		if (row == N) {
			answer++;
			return;
		}

		for (int j = 0; j < N; j++) {
			boolean can = true;
			for (int i = row; i >= 0; i--) {
				if (arr[i] == j || arr[i]-(row-i) == j || arr[i]+(row-i)==j)
					can = false;
			}
			if (can) {
				arr[row] = j;
				recursion2(N, arr, row + 1);
				arr[row] = -1;
			}
		}

	}
}