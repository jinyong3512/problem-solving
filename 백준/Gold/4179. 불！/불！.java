import java.util.*;
import java.io.*;

class Data {
	int i;
	int j;
	int time;

	Data(int i, int j, int time) {
		this.i = i;
		this.j = j;
		this.time = time;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int R, C;
		char[][] arr;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];

		for (int i = 0; i < R; i++) {
			String input_line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = input_line.charAt(j);
			}
		}

		///////////////////////////////////////////////////////

		int init_i = -1;
		int init_j = -1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 'J') {
					init_i = i;
					init_j = j;
					break;
				}
			}
		}

		Queue<Data> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];

		queue.add(new Data(init_i, init_j, 0));
		visited[init_i][init_j] = true;

		int[][] time_fire = new int[R][C];
		complete_time_fire(arr, time_fire);

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			if (tmp.i == 0 || tmp.i == R - 1 || tmp.j == 0 || tmp.j == C - 1) {
				System.out.println(tmp.time + 1);
				System.exit(0);
			}

			// 위로
			if (tmp.i - 1 >= 0 && arr[tmp.i - 1][tmp.j] == '.' && !visited[tmp.i - 1][tmp.j]
					&& tmp.time + 1 < time_fire[tmp.i - 1][tmp.j]) {
				visited[tmp.i - 1][tmp.j] = true;
				queue.add(new Data(tmp.i - 1, tmp.j, tmp.time + 1));
			}

			// 아래로
			if (tmp.i + 1 < R && arr[tmp.i + 1][tmp.j] == '.' && !visited[tmp.i + 1][tmp.j]
					&& tmp.time + 1 < time_fire[tmp.i + 1][tmp.j]) {
				visited[tmp.i + 1][tmp.j] = true;
				queue.add(new Data(tmp.i + 1, tmp.j, tmp.time + 1));
			}

			// 왼쪽
			if (tmp.j - 1 >= 0 && arr[tmp.i][tmp.j - 1] == '.' && !visited[tmp.i][tmp.j - 1]
					&& tmp.time + 1 < time_fire[tmp.i][tmp.j - 1]) {
				visited[tmp.i][tmp.j - 1] = true;
				queue.add(new Data(tmp.i, tmp.j - 1, tmp.time + 1));
			}

			// 오른쪽
			if (tmp.j + 1 < C && arr[tmp.i][tmp.j + 1] == '.' && !visited[tmp.i][tmp.j + 1]
					&& tmp.time + 1 < time_fire[tmp.i][tmp.j + 1]) {
				visited[tmp.i][tmp.j + 1] = true;
				queue.add(new Data(tmp.i, tmp.j + 1, tmp.time + 1));
			}
		}

		System.out.println("IMPOSSIBLE");

	}

	public static void complete_time_fire(char[][] arr, int[][] time_fire) {

		Queue<Data> queue = new LinkedList<>();
		boolean[][] visited = new boolean[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 'F') {
					visited[i][j] = true;
					queue.add(new Data(i, j, 0));
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				time_fire[i][j] = Integer.MAX_VALUE;
			}
		}

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();
			time_fire[tmp.i][tmp.j] = tmp.time;

			// 위로
			if (tmp.i - 1 >= 0 && arr[tmp.i - 1][tmp.j] != '#' && !visited[tmp.i - 1][tmp.j]) {
				visited[tmp.i - 1][tmp.j] = true;
				queue.add(new Data(tmp.i - 1, tmp.j, tmp.time + 1));
			}

			// 아래로
			if (tmp.i + 1 < arr.length && arr[tmp.i + 1][tmp.j] != '#' && !visited[tmp.i + 1][tmp.j]) {
				visited[tmp.i + 1][tmp.j] = true;
				queue.add(new Data(tmp.i + 1, tmp.j, tmp.time + 1));
			}

			// 왼쪽
			if (tmp.j - 1 >= 0 && arr[tmp.i][tmp.j - 1] != '#' && !visited[tmp.i][tmp.j - 1]) {
				visited[tmp.i][tmp.j - 1] = true;
				queue.add(new Data(tmp.i, tmp.j - 1, tmp.time + 1));
			}

			// 오른쪽
			if (tmp.j + 1 < arr[0].length && arr[tmp.i][tmp.j + 1] != '#' && !visited[tmp.i][tmp.j + 1]) {
				visited[tmp.i][tmp.j + 1] = true;
				queue.add(new Data(tmp.i, tmp.j + 1, tmp.time + 1));

			}

		}
	}
}