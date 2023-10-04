import java.io.*;
import java.util.*;

class Data {
	int y, x;
	boolean[] have_keys;
	int depth;

	Data(int y, int x, boolean[] have_keys, int depth) {
		this.y = y;
		this.x = x;
		this.have_keys = have_keys;
		this.depth = depth;
	}
}

public class Main {

	public static int[] DY = new int[] { 1, -1, 0, 0 };
	public static int[] DX = new int[] { 0, 0, 1, -1 };
	public static char[] KEYS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f' };
	public static char[] DOORS = new char[] { 'A', 'B', 'C', 'D', 'E', 'F' };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		char[][] arr;

		////////////////////////////////////////////////////////////////

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];

		for (int i = 0; i < arr.length; i++) {
			String input_line = br.readLine();
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = input_line.charAt(j);
			}
		}

		///////////////////////////////////////////////////////////////

		Queue<Data> queue = new LinkedList<>();

		// 시작 지점 찾기
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == '0') {
					queue.add(new Data(i, j, new boolean[6], 0));
				}
			}
		}

		ArrayList<ArrayList<boolean[]>> big_arrayList = new ArrayList<>();
		for (int i = 0; i < N * M; i++)
			big_arrayList.add(new ArrayList<boolean[]>());

		big_arrayList.get(queue.peek().y * M + queue.peek().x).add(new boolean[6]);

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();
			
//			System.out.println(" y = " + tmp.y + " x = " + tmp.x);
//			System.out.println(tmp.have_keys[0] + " " + tmp.have_keys[1] + " " + tmp.have_keys[2] + " " + tmp.have_keys[3] +" " + tmp.have_keys[4] + " " + tmp.have_keys[5]);

			if (arr[tmp.y][tmp.x] == '1') {
				sb.append(tmp.depth);
				break;
			}

			for (int d = 0; d < 4; d++) {
				int new_y = tmp.y + DY[d];
				int new_x = tmp.x + DX[d];
				boolean[] new_have_keys = new boolean[6];
				for (int i = 0; i < 6; i++) {
					new_have_keys[i] = tmp.have_keys[i];
				}

				if (new_y >= 0 && new_x >= 0 && new_y < arr.length && new_x < arr[0].length) {

					boolean can = true;

					if (arr[new_y][new_x] == '#')
						can = false;
					for (int i = 0; i < 6; i++) {
						if (arr[new_y][new_x] == KEYS[i]) {
							new_have_keys[i] = true;
						}
						if (arr[new_y][new_x] == DOORS[i]) {
							if (!new_have_keys[i])
								can = false;
						}
					}
					if (can) {
						boolean find_same = false;
						for (int i = 0; i < big_arrayList.get(new_y * M + new_x).size(); i++) {
							boolean find_same_sub = true;
							for (int j = 0; j < 6; j++) {
								if (new_have_keys[j] != big_arrayList.get(new_y * M + new_x).get(i)[j]) {
									find_same_sub = false;
								}
							}
							if (find_same_sub)
								find_same = true;
						}

						if (!find_same) {
							big_arrayList.get(new_y * M + new_x).add(new_have_keys);
							queue.add(new Data(new_y, new_x, new_have_keys, tmp.depth + 1));
						}

					}
				}
			}
		}

		if (sb.length() == 0)
			sb.append("-1");

		System.out.println(sb);

	}
}