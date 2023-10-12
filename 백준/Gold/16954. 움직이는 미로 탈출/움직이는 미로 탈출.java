import java.util.*;
import java.io.*;

public class Main {
	static char[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[8][8];
		for (int i = 0; i < 8; i++) {
			char[] cs = br.readLine().trim().toCharArray();
			for (int j = 0; j < 8; j++) {
				board[i][j] = cs[j];
			}
		}

		System.out.println(bfs());

	}

	public static int bfs() {
		boolean[][] visited = new boolean[8][8];
		int[] dx = { 0, -1, 0, 1, 0, 1, 1, -1, -1 };
		int[] dy = { -1, 0, 1, 0, 0,  1, -1, 1, -1 };
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 7 });
		visited[7][0] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				 visited = new boolean[8][8];
				int now[] = q.poll();
				int x = now[0], y = now[1];
				if (board[y][x] == '#') {
					continue;
				}
				if (x == 7 && y == 0) {
					return 1;
				}

				for (int d = 0; d < 9; d++) {

					int nx = dx[d] + x, ny = dy[d] + y;

					if (nx < 0 || nx >= 8 || ny >= 8 || ny < 0 || visited[ny][nx] || board[ny][nx] == '#') {
						continue;
					}
					visited[ny][nx] = true;
					q.add(new int[] { nx, ny });

				}
			}
			wall_move();

		}

		return 0;
	}

	public static void wall_move() {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == '#') {
					board[i][j] = '.';

					if (i != 7) {
						board[i + 1][j] = '#';
					}
				}
			}
		}
	}

}