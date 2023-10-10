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

		/*
		 * dfs로 매번 확인해야 한다 하지만 dfs 자체가 겹치는 부분이 너무 많음 dp다
		 */

		int test_case = 0;

		while (true) {

			test_case++;

			int N;
			int[][] arr;

			//////////////////////////////////////////////////////////////////

			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			//////////////////////////////////////////////////////////////////

			// 해당 i,j 지점을 방문할때 최솟값!!!
			long[][] dp = new long[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}

			dp[0][0] = arr[0][0];

			Queue<Point> queue = new LinkedList<>();
			queue.add(new Point(0, 0));

			while (!queue.isEmpty()) {
				Point tmp = queue.remove();

				// 위로
				if (tmp.y - 1 >= 0 && dp[tmp.y][tmp.x] + arr[tmp.y - 1][tmp.x] < dp[tmp.y - 1][tmp.x]) {
					dp[tmp.y - 1][tmp.x] = dp[tmp.y][tmp.x] + arr[tmp.y - 1][tmp.x];
					queue.add(new Point(tmp.y - 1, tmp.x));
				}
				// 아래로
				if (tmp.y + 1 < arr.length && dp[tmp.y][tmp.x] + arr[tmp.y + 1][tmp.x] < dp[tmp.y + 1][tmp.x]) {
					dp[tmp.y + 1][tmp.x] = dp[tmp.y][tmp.x] + arr[tmp.y + 1][tmp.x];
					queue.add(new Point(tmp.y + 1, tmp.x));
				}
				// 왼쪽
				if (tmp.x - 1 >= 0 && dp[tmp.y][tmp.x] + arr[tmp.y][tmp.x - 1] < dp[tmp.y][tmp.x - 1]) {
					dp[tmp.y][tmp.x - 1] = dp[tmp.y][tmp.x] + arr[tmp.y][tmp.x - 1];
					queue.add(new Point(tmp.y, tmp.x - 1));
				}
				// 아래로
				if (tmp.x + 1 < arr[0].length && dp[tmp.y][tmp.x] + arr[tmp.y][tmp.x + 1] < dp[tmp.y][tmp.x + 1]) {
					dp[tmp.y][tmp.x + 1] = dp[tmp.y][tmp.x] + arr[tmp.y][tmp.x + 1];
					queue.add(new Point(tmp.y, tmp.x + 1));
				}

			}

			sb.append("Problem ").append(test_case).append(": ").append(dp[N-1][N-1]).append("\n");

		}
		System.out.println(sb);

	}

}
