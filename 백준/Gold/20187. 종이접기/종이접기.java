import java.io.*;
import java.util.*;

class Mini_square {
	int hole;

	Mini_square(int hole) {
		this.hole = hole;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int k = Integer.parseInt(br.readLine());
		String[] command = new String[2 * k];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * k; i++)
			command[i] = st.nextToken();

		int init_hole = Integer.parseInt(br.readLine());

		/////////////////////////////////////////////////////////////

		Mini_square[][] map = new Mini_square[1][1];
		map[0][0] = new Mini_square(init_hole);

		for (int i = 2 * k - 1; i >= 0; i--) {
			int N = map.length;
			int M = map[0].length;

			if (command[i].equals("D")) {
				// 위가 늘어남 아래는 그대로
				Mini_square[][] new_map = new Mini_square[2 * N][M];

				// 아래 그대로
				for (int y = N; y < 2 * N; y++) {
					for (int x = 0; x < M; x++) {
						new_map[y][x] = new Mini_square(map[y - N][x].hole);
					}
				}

				// 위 뒤집어
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < M; x++) {
						int hole = map[N - 1 - y][x].hole;
						if (hole == 0)
							hole = 2;
						else if (hole == 1)
							hole = 3;
						else if (hole == 2)
							hole = 0;
						else
							hole = 1;

						new_map[y][x] = new Mini_square(hole);
					}
				}

				map = new_map;

			} else if (command[i].contentEquals("U")) {
				// 아래가 늘어남 위는 그대로
				Mini_square[][] new_map = new Mini_square[N * 2][M];

				// 위는 그대로
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < M; x++) {
						new_map[y][x] = new Mini_square(map[y][x].hole);
					}
				}

				// 아래 뒤집어
				for (int y = N; y < N * 2; y++) {
					for (int x = 0; x < M; x++) {
						int hole = map[2 * N - 1 - y][x].hole;
						if (hole == 0)
							hole = 2;
						else if (hole == 1)
							hole = 3;
						else if (hole == 2)
							hole = 0;
						else
							hole = 1;
						new_map[y][x] = new Mini_square(hole);
					}
				}

				map = new_map;

			} else if (command[i].contentEquals("R")) {
				// 왼쪽이 늘어남 오른쪽은 그대로
				Mini_square[][] new_map = new Mini_square[N][M * 2];

				// 왼쪽 뒤집어
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < M; x++) {
						int hole = map[y][M - 1 - x].hole;
						if (hole == 0)
							hole = 1;
						else if (hole == 1)
							hole = 0;
						else if (hole == 2)
							hole = 3;
						else
							hole = 2;
						new_map[y][x] = new Mini_square(hole);
					}
				}

				// 오른쪽 그대로
				for (int y = 0; y < N; y++) {
					for (int x = M; x < M * 2; x++) {
						new_map[y][x] = new Mini_square(map[y][x - M].hole);
					}
				}

				map = new_map;

			} else if (command[i].contentEquals("R")) {
				Mini_square[][] new_map = new Mini_square[N][M * 2];

				// 오른쪽이 늘어남 왼쪽은 그대로

				// 왼쪽 그대로
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < M; x++) {
						new_map[y][x] = new Mini_square(map[y][x].hole);
					}
				}

				// 오른쪽 뒤집어
				for (int y = 0; y < N; y++) {
					for (int x = M; x < M * 2; x++) {
						int hole = map[y][M-1-(x-M)].hole;
						if (hole == 0)
							hole = 1;
						else if (hole == 1)
							hole = 0;
						else if (hole == 2)
							hole = 3;
						else
							hole = 2;
						new_map[y][x] = new Mini_square(hole);
					}
				}

				map = new_map;

			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++)
				System.out.print(map[i][j].hole + " ");
			System.out.println();
		}
	}
}