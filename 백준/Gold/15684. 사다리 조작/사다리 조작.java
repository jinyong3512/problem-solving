import java.io.*;
import java.util.*;

public class Main {

	public static boolean find_can = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M, H;
		int[][] arr; // 0은 밑으로 내려가 1은 오른쪽으로이동 2은 왼쪽으로 이동

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[a - 1][b - 1] = 1;
			arr[a - 1][b - 1 + 1] = 2;
		}

		///////////////////////////////////////////////////////////

		// (0개 놓았을때 놓을 수 있는 경우의 수) C (0,1,2,3);

		int answer = -1;

		for (int r = 0; r <= 3; r++) {
			recursion(arr, r, 0, 0, -1);
			if (find_can) {
				answer = r;
				break;
			}
		}

		System.out.println(answer);
	}

	public static void recursion(int[][] arr, int r, int depth, int last_i, int last_j) {

		if (depth == r) {
			boolean can = true;

			for (int j = 0; j < arr[0].length; j++) {
				if (j != where_is_my_goal(arr, j))
					can = false;
			}

			if (can)
				find_can = can;

			return;
		}

		for (int i = last_i; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length - 1; j++) {
				if (arr[i][j] == 0) {

					// 오른쪽에 출발 사다리 있어
					if (arr[i][j + 1] == 1) {
						continue;
					}
					arr[i][j] = 1;
					arr[i][j + 1] = 2;
					recursion(arr, r, depth + 1, i, j);
					arr[i][j] = 0;
					arr[i][j + 1] = 0;
				}
			}
		}

	}

	public static int where_is_my_goal(int[][] arr, int start) {
		int x = start;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][x] == 1) {
				x = x + 1;

			} else if (arr[i][x] == 2)
				x = x - 1;
		}

		return x;
	}
}