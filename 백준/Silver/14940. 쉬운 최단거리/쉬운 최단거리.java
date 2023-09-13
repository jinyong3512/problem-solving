import java.io.*;
import java.util.*;

class Point {
	int y, x, depth;

	Point(int y, int x, int depth) {
		this.y = y;
		this.x = x;
		this.depth = depth;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n, m;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		////////////////////////////////////////

		boolean[][] visited = new boolean[n][m];
		int[][] answer = new int[n][m];
		Queue<Point> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				answer[i][j] = -1;

				if (arr[i][j] == 2) {
					visited[i][j] = true;
					queue.add(new Point(i, j, 0));
				} else if (arr[i][j] == 0) {
					answer[i][j] = 0;
				}
			}
		}

		while (!queue.isEmpty()) {
			Point tmp = queue.remove();

			answer[tmp.y][tmp.x] = tmp.depth;

			// 위로
			if (tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] == 1) {
				visited[tmp.y - 1][tmp.x] = true;
				queue.add(new Point(tmp.y - 1, tmp.x, tmp.depth + 1));
			}

			// 아래로
			if (tmp.y + 1 < arr.length && !visited[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] == 1) {
				visited[tmp.y + 1][tmp.x] = true;
				queue.add(new Point(tmp.y + 1, tmp.x, tmp.depth + 1));
			}

			// 왼쪽
			if (tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] == 1) {
				visited[tmp.y][tmp.x - 1] = true;
				queue.add(new Point(tmp.y, tmp.x - 1, tmp.depth + 1));
			}

			// 오른쪽
			if (tmp.x + 1 < arr[0].length && !visited[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] == 1) {
				visited[tmp.y][tmp.x + 1] = true;
				queue.add(new Point(tmp.y, tmp.x + 1, tmp.depth + 1));
			}

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}