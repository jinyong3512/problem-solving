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

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int L, W;
		char[][] map;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[L][W];

		for (int i = 0; i < L; i++) {
			String line = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		/////////////////////////////////////////////////////////////

		int answer = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 'L') {
					boolean[][] visited = new boolean[map.length][map[0].length];
					Queue<Data> queue = new LinkedList<>();

					visited[i][j] = true;
					queue.add(new Data(i, j, 0));

					while (!queue.isEmpty()) {
						Data tmp = queue.remove();

						answer = Math.max(answer, tmp.depth);

						// 위로
						if (tmp.y - 1 >= 0 && !visited[tmp.y - 1][tmp.x] && map[tmp.y - 1][tmp.x] == 'L') {
							visited[tmp.y - 1][tmp.x] = true;
							queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1));
						}

						// 아래로
						if (tmp.y + 1 < map.length && !visited[tmp.y + 1][tmp.x] && map[tmp.y + 1][tmp.x] == 'L') {
							visited[tmp.y + 1][tmp.x] = true;
							queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1));
						}

						// 왼쪽
						if (tmp.x - 1 >= 0 && !visited[tmp.y][tmp.x-1] && map[tmp.y][tmp.x-1] == 'L') {
							visited[tmp.y ][tmp.x-1] = true;
							queue.add(new Data(tmp.y , tmp.x-1, tmp.depth + 1));
						}

						// 오른쪽
						if (tmp.x + 1 < map[0].length && !visited[tmp.y ][tmp.x+ 1] && map[tmp.y ][tmp.x+ 1] == 'L') {
							visited[tmp.y ][tmp.x+ 1] = true;
							queue.add(new Data(tmp.y , tmp.x+ 1, tmp.depth + 1));
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}