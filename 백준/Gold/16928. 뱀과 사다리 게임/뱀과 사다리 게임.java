import java.io.*;
import java.util.*;

class Data {
	int num;
	int depth;

	Data(int num, int depth) {
		this.num = num;
		this.depth = depth;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		int[][] jump;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		jump = new int[N + M][2];

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			jump[i][0] = Integer.parseInt(st.nextToken());
			jump[i][1] = Integer.parseInt(st.nextToken());
		}

		/////////////////////////////////////////////////////

		Queue<Data> queue = new LinkedList<>();
		boolean[] visited = new boolean[101];

		visited[1] = true;
		queue.add(new Data(1, 0));

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			for (int i = 0; i < jump.length; i++) {
				if (tmp.num == jump[i][0]) {
					tmp.num = jump[i][1];
					visited[tmp.num] = true;
				}
			}

			if (tmp.num == 100) {
				System.out.println(tmp.depth);
				break;
			}

			for (int i = 1; i <= 6; i++) {
				if (tmp.num + i <= 100 && !visited[tmp.num + i]) {
					visited[tmp.num + i] = true;
					queue.add(new Data(tmp.num + i, tmp.depth + 1));
				}
			}

		}

	}
}