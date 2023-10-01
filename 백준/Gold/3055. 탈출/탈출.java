import java.io.*;
import java.util.*;

class Data {
	int y, x, depth;

	Data(int y, int x, int depth) {
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

		int R, C;
		char[][] arr;

		////////////////////////////////////////
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}

		////////////////////////////////////////

		int[][] water_times = new int[arr.length][arr[0].length];
		for (int i = 0; i < water_times.length; i++) {
			for (int j = 0; j < water_times[0].length; j++)
				water_times[i][j] = Integer.MAX_VALUE;
		}

		spread_water(arr, water_times);

		boolean[][] visited = new boolean[arr.length][arr[0].length];
		Queue<Data> queue = new LinkedList<>();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 'S') {
					visited[i][j] = true;
					queue.add(new Data(i, j, 0));
				}
			}
		}

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			if (arr[tmp.y][tmp.x] == 'D') {
				sb.append(tmp.depth);
				break;
			}

			if (water_times[tmp.y][tmp.x] <= tmp.depth)
				continue;

			// 위로
			if (tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] != 'X') {
				visited[tmp.y - 1][tmp.x] = true;
				queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1));
			}

			// 아래
			if (tmp.y + 1 < arr.length && !visited[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] != 'X') {
				visited[tmp.y + 1][tmp.x] = true;
				queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1));
			}

			// 왼쪽
			if (tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] != 'X') {
				visited[tmp.y][tmp.x - 1] = true;
				queue.add(new Data(tmp.y, tmp.x - 1, tmp.depth + 1));
			}

			// 오른쪽
			if (tmp.x + 1 < arr[0].length && !visited[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] != 'X') {
				visited[tmp.y][tmp.x + 1] = true;
				queue.add(new Data(tmp.y, tmp.x + 1, tmp.depth + 1));
			}

		}

		if (sb.length() == 0) {
			sb.append("KAKTUS");
		}

		System.out.println(sb);

	}

	public static void spread_water(char[][] arr, int[][] water_times) {
		boolean[][] visited = new boolean[arr.length][arr[0].length];
		Queue<Data> queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == '*') {
					visited[i][j] = true;
					queue.add(new Data(i, j, 0));
				}
			}
		}

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			water_times[tmp.y][tmp.x] = tmp.depth;

			// 위로
			if (tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x] && arr[tmp.y - 1][tmp.x] != 'X'
					&& arr[tmp.y - 1][tmp.x] != 'D') {
				visited[tmp.y - 1][tmp.x] = true;
				queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1));
			}

			// 아래
			if (tmp.y + 1 < arr.length && !visited[tmp.y + 1][tmp.x] && arr[tmp.y + 1][tmp.x] != 'X'
					&& arr[tmp.y + 1][tmp.x] != 'D') {
				visited[tmp.y + 1][tmp.x] = true;
				queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1));
			}

			// 왼쪽
			if (tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x - 1] && arr[tmp.y][tmp.x - 1] != 'X'
					&& arr[tmp.y][tmp.x - 1] != 'D') {
				visited[tmp.y][tmp.x - 1] = true;
				queue.add(new Data(tmp.y, tmp.x - 1, tmp.depth + 1));
			}

			// 오른쪽
			if (tmp.x + 1 < arr[0].length && !visited[tmp.y][tmp.x + 1] && arr[tmp.y][tmp.x + 1] != 'X'
					&& arr[tmp.y][tmp.x + 1] != 'D') {
				visited[tmp.y][tmp.x + 1] = true;
				queue.add(new Data(tmp.y, tmp.x + 1, tmp.depth + 1));
			}

		}

	}
}