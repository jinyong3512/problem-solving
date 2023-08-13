import java.io.*;
import java.util.*;

class Main {

	public static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 4;
		for (int t = 0; t < T; t++) {
			int[][] arr = new int[6][3];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
//				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			/////////////////////////////////////////////////////

			boolean can = true;

			for (int i = 0; i < 6; i++) {
				int sum = 0;
				for (int j = 0; j < 3; j++) {
					sum += arr[i][j];
				}
				if (sum != 5)
					can = false;
			}

			answer = 0;

			if (can) {
				boolean[] visited = new boolean[6];
				recursion_1(arr, arr.length, arr[0][0], 0, 0, 0, visited);
				sb.append(answer).append(" ");
			} else {
				sb.append(answer).append(" ");
			}
		}

		System.out.println(sb);

	}

	public static void recursion_1(int[][] arr, int n, int r, int now_team, int index, int depth, boolean[] visited) {

		if (depth == r) {
//			System.out.println(
//					" recursion_1  r = " + r + " now_team = " + now_team + " index = " + index + " depth = " + depth);

			recursion_2(arr, n, arr[now_team][1], now_team, now_team, 0,visited);

			return;
		}

		for (int i = index + 1; i < arr.length; i++) {
			if (arr[i][2] > 0 && !visited[i]) {
				arr[now_team][0]--;
				arr[i][2]--;
				visited[i] = true;
				recursion_1(arr, n, r, now_team, i, depth + 1, visited);
				visited[i] = false;
				arr[now_team][0]++;
				arr[i][2]++;
			}
		}

	}

	public static void recursion_2(int[][] arr, int n, int r, int now_team, int index, int depth, boolean[] visited) {

		if (depth == r) {
//			System.out.println(
//					" recursion_2  r = " + r + " now_team = " + now_team + " index = " + index + " depth = " + depth);

			recursion_3(arr, n, arr[now_team][2], now_team, now_team, 0,visited);

			return;
		}

		for (int i = index + 1; i < arr.length; i++) {
			if (arr[i][1] > 0 && !visited[i]) {
				arr[now_team][1]--;
				arr[i][1]--;
				visited[i] = true;
				recursion_2(arr, n, r, now_team, i, depth + 1, visited);
				visited[i] = false;
				arr[now_team][1]++;
				arr[i][1]++;
			}
		}

	}

	public static void recursion_3(int[][] arr, int n, int r, int now_team, int index, int depth, boolean[] visited) {

		if (depth == r) {

			if (now_team == 5) {
				if (can(arr))
					answer = 1;
				return;
			}

//			System.out.println(
//					" recursion_3  r = " + r + " now_team = " + now_team + " index = " + index + " depth = " + depth);
//			System.out.println("");

			boolean[] new_visited = new boolean[6];
			recursion_1(arr, n, arr[now_team + 1][0], now_team + 1, now_team + 1, 0, new_visited);

			return;
		}

		for (int i = index + 1; i < arr.length; i++) {
			if (arr[i][0] > 0 && !visited[i]) {
				arr[now_team][2]--;
				arr[i][0]--;
				visited[i] = true;
				recursion_3(arr, n, r, now_team, i, depth + 1, visited);
				visited[i] = false;
				arr[now_team][2]++;
				arr[i][0]++;
			}
		}

	}

	public static boolean can(int[][] arr) {
		boolean success = true;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] != 0)
					success = false;
			}
		}

		return success;
	}

}
