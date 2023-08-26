import java.io.*;
import java.util.*;

// 

class Solution {

	public static int answer_core_sum;
	public static int answer_line_sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) { // 테케만큼 돌기

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

			/////////////////////////////////////////////////////////
			answer_core_sum = Integer.MIN_VALUE;

			recursion(arr, 0, 0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(answer_line_sum).append("\n");
		}

		System.out.println(sb);

	}

	public static void recursion(int[][] arr, int y, int x, int core_sum, int line_sum) {
		if (y == arr.length) {
			if (answer_core_sum < core_sum) {
				answer_core_sum = core_sum;
				answer_line_sum = line_sum;
			} else if (answer_core_sum == core_sum) {
				answer_line_sum = Math.min(answer_line_sum, line_sum);
			}

			return;
		}

		if (arr[y][x] == 0 || arr[y][x] == 2) {
			if (x == arr[0].length - 1)
				recursion(arr, y + 1, 0, core_sum, line_sum);
			else
				recursion(arr, y, x + 1, core_sum, line_sum);
		} else {
			if (y == 0 || y == arr.length - 1 || x == 0 || x == arr[0].length - 1) {
				if (x == arr[0].length - 1)
					recursion(arr, y + 1, 0, core_sum + 1, line_sum);
				else
					recursion(arr, y, x + 1, core_sum + 1, line_sum);
			} else {

				// 위로 연결
				boolean can = true;
				for (int i = y - 1; i >= 0; i--) {
					if (arr[i][x] != 0) {
						can = false;
					}
				}
				if (can) {
					for (int i = y - 1; i >= 0; i--) {
						arr[i][x] = 2;
					}
					recursion(arr, y, x + 1, core_sum + 1, line_sum + y);
					for (int i = y - 1; i >= 0; i--) {
						arr[i][x] = 0;
					}
				}

				// 아래로 연결
				can = true;
				for (int i = y + 1; i < arr.length; i++) {
					if (arr[i][x] != 0) {
						can = false;
					}
				}
				if (can) {
					for (int i = y + 1; i < arr.length; i++) {
						arr[i][x] = 2;
					}
					recursion(arr, y, x + 1, core_sum + 1, line_sum + arr.length - y - 1);
					for (int i = y + 1; i < arr.length; i++) {
						arr[i][x] = 0;
					}
				}

				// 왼쪽 연결
				can = true;
				for (int j = x - 1; j >= 0; j--) {
					if (arr[y][j] != 0) {
						can = false;
					}
				}
				if (can) {
					for (int j = x - 1; j >= 0; j--) {
						arr[y][j] = 2;
					}
					recursion(arr, y, x + 1, core_sum + 1, line_sum + x);
					for (int j = x - 1; j >= 0; j--) {
						arr[y][j] = 0;
					}
				}

				// 오른쪽 연결
				can = true;
				for (int j = x + 1; j < arr[0].length; j++) {
					if (arr[y][j] != 0) {
						can = false;
					}
				}
				if (can) {
					for (int j = x + 1; j < arr[0].length; j++) {
						arr[y][j] = 2;
					}
					recursion(arr, y, x + 1, core_sum + 1, line_sum + arr[0].length - x - 1);
					for (int j = x + 1; j < arr[0].length; j++) {
						arr[y][j] = 0;
					}
				}
				
				// 그냥 연결 안할래 ㅋ
				recursion(arr,y,x+1,core_sum,line_sum);
			}

		}
	}
}