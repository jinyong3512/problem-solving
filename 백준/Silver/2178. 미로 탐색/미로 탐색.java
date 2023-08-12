import java.io.*;
import java.util.*;

class Point {
	int y;
	int x;
	int depth;

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

		int N, M;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		//////////////////////////////////////////

		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1));

		while (!queue.isEmpty()) {
			Point tmp = queue.remove();
			if (tmp.y == N - 1 && tmp.x == M - 1) {
				System.out.println(tmp.depth);
				break;
			}

			// 위로
			if (tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] == 1) {
				visited[tmp.y - 1][tmp.x] = true;
				queue.add(new Point(tmp.y - 1, tmp.x, tmp.depth + 1));
			}

			// 아래로
			if (tmp.y + 1 < N && !visited[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] == 1) {
				visited[tmp.y + 1][tmp.x] = true;
				queue.add(new Point(tmp.y + 1, tmp.x, tmp.depth + 1));
			}

			// 왼쪽
			if (tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] == 1) {
				visited[tmp.y][tmp.x - 1] = true;
				queue.add(new Point(tmp.y, tmp.x - 1, tmp.depth + 1));
			}
			// 오른쪽
			if (tmp.x + 1 < M && !visited[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] == 1) {
				visited[tmp.y][tmp.x + 1] = true;
				queue.add(new Point(tmp.y, tmp.x + 1, tmp.depth + 1));
			}
		}

	}
}