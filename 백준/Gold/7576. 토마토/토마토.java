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

		int M, N;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		///////////////////////////////////////

		Queue<Data> queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1)
					queue.add(new Data(i, j, 0));
			}
		}

		int answer = 0;

		while (!queue.isEmpty()) {
			Data tmp = queue.remove();

			answer = Math.max(answer, tmp.depth);

			if (tmp.y - 1 >= 0 && arr[tmp.y - 1][tmp.x] == 0) {
				arr[tmp.y - 1][tmp.x] = 1;
				queue.add(new Data(tmp.y - 1, tmp.x, tmp.depth + 1));
			}
			if (tmp.y + 1 < arr.length && arr[tmp.y + 1][tmp.x] == 0) {
				arr[tmp.y + 1][tmp.x] = 1;
				queue.add(new Data(tmp.y + 1, tmp.x, tmp.depth + 1));
			}
			if (tmp.x - 1 >= 0 && arr[tmp.y][tmp.x - 1] == 0) {
				arr[tmp.y][tmp.x - 1] = 1;
				queue.add(new Data(tmp.y, tmp.x - 1, tmp.depth + 1));
			}
			if (tmp.x + 1 < arr[0].length && arr[tmp.y][tmp.x + 1] == 0) {
				arr[tmp.y][tmp.x + 1] = 1;
				queue.add(new Data(tmp.y, tmp.x + 1, tmp.depth + 1));
			}
		}
		
		
		boolean can = true;
		
		for(int i =0 ; i < N ; i++) {
			for(int j = 0; j < M ; j++) {
				if(arr[i][j] == 0 )
					can = false;
			}
		}
		
		if(can)
			System.out.println(answer);
		else
			System.out.println("-1");

	}
}
