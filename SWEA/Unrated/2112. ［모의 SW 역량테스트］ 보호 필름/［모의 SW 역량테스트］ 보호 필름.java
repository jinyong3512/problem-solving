import java.io.*;
import java.util.*;

public class Solution {

	public static int answer;
	public static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int D, W;
			boolean[][] arr;

			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new boolean[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					if (Integer.parseInt(st.nextToken()) == 1)
						arr[i][j] = true;
				}
			}

			///////////////////////////////////////////////////

			answer = -1;
			for (int r = 0; r <= K; r++) {
				recursion(D, r, arr, 0, -1);
				if (answer != -1)
					break;
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");

		}
		System.out.println(sb);
	}

	public static int check(boolean[][] arr, int K) {
		int min = Integer.MAX_VALUE;

		for (int j = 0; j < arr[0].length; j++) {
			int j_max = Integer.MIN_VALUE;
			int A_count = 0;
			int B_count = 0;

			if (arr[0][j]) {
				A_count++;
			} else
				B_count++;

			for (int i = 1; i < arr.length; i++) {
				if (arr[i][j]) {
					A_count++;
					B_count = 0;
					j_max = Math.max(j_max, A_count);
				} else {
					A_count = 0;
					B_count++;
					j_max = Math.max(j_max, B_count);
				}
			}
			min = Math.min(min, j_max);

		}
		return min;
	}

	public static void recursion(int n, int r, boolean[][] arr, int depth, int index) {

		if (check(arr, K)+r-depth < K - 1)
			return;

		if (depth == r) {
			if (check(arr, K) >= K)
				answer = r;
			return;
		}

		for (int i = index + 1; i < n; i++) {

			boolean[][] new_arr = new boolean[arr.length][arr[0].length];
			for (int y = 0; y < arr.length; y++) {
				for (int x = 0; x < arr[0].length; x++) {
					new_arr[y][x] = arr[y][x];
				}
			}
			// A로 바꿔
			for (int x = 0; x < arr[0].length; x++) {
				new_arr[i][x] = true;
			}
			recursion(n, r, new_arr, depth + 1, i);

			new_arr = new boolean[arr.length][arr[0].length];
			for (int y = 0; y < arr.length; y++) {
				for (int x = 0; x < arr[0].length; x++) {
					new_arr[y][x] = arr[y][x];
				}
			}
			// B로 바꿔
			for (int x = 0; x < arr[0].length; x++) {
				new_arr[i][x] = false;
			}
			recursion(n, r, new_arr, depth + 1, i);

		}

	}
}