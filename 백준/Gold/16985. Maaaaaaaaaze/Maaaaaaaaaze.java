
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int[][][] maze = new int[5][5][5];
	static int ans;
	static int[] order = new int[5];
	static boolean[] selected = new boolean[5];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int h = 0; h < 5; h++) {
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < 5; j++) {
					maze[h][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		ans = Integer.MAX_VALUE;
		escape(0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

	static public int bfs() {
		if ( maze[order[0]][0][0] == 0) {
			return -1;
		}
		int[] dx = { 0, -1, 0, 1 };
		int[] dy = { -1, 0, 1, 0 };
		int[] dh = { -1, 1 };

		boolean[][][] visited = new boolean[5][5][5];
		visited[0][0][0] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0, 0, 0 });
//		for (int h = 0; h < 5; h++) {
//			for (int i = 0; i < 5; i++) {
//				System.out.println(Arrays.toString(maze[h][i]));
//			}
//		}
		while (!q.isEmpty()) {
			int now[] = q.poll();
			int h = now[0], y = now[1], x = now[2], cnt = now[3];
			if (h == 4 && y == 4 && x == 4) {
				return cnt;

			}
			for (int d = 0; d < 2; d++) {
				int nh = h + dh[d];
				if (nh < 0 || nh >= 5 || visited[nh][y][x] || maze[order[nh]][y][x] == 0) {
					continue;
				}
				visited[nh][y][x] = true;
				q.add(new int[] { nh, y, x, cnt + 1 });
			}
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5 || visited[h][ny][nx] || maze[order[h]][ny][nx] == 0) {
					continue;
				}
				visited[h][ny][nx] = true;
				q.add(new int[] { h, ny, nx, cnt + 1 });
			}
		}

		return -1;
	}

	static public void rotateRight(int h) {
		int[][] Temp = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Temp[i][j] = maze[order[h]][5 - j - 1][i];
			}
		}

		maze[order[h]] = Temp.clone();

	}



	static public void rotate(int idx) {

		if (idx == 5) {

			int res = bfs();
			if (res != -1) {
				ans = Math.min(ans, res);
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			rotateRight(order[idx]);
			
			
			rotate(idx + 1);
		}
		

	}

	static public void escape(int cnt) {
		if (cnt == 5) {
			rotate(0);
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (!selected[i]) {
				selected[i] = true;
				order[cnt] = i;
				escape(cnt + 1);
				selected[i] = false;
			}

		}
	}

}
