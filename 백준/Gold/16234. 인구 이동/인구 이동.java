import java.io.*;
import java.util.*;

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, L, R;
		int[][] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		/////////////////////////////////////////////

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//////////////////////////////////////////////////

		int day = 0;
		
		while (true) {
			boolean stop = true;
			
			ArrayList<Point> arrayList;
			boolean[][] visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						arrayList = new ArrayList<>();
						dfs(arr, arrayList, i, j, visited, L, R);
						
						if(arrayList.size()>1)
							stop = false;

						int sum = 0;
						for (Point point : arrayList)
							sum += arr[point.y][point.x];

						for (Point point : arrayList)
							arr[point.y][point.x] = sum / arrayList.size();
					}
				}
			}
			
			if(stop)
				break;
			
			day++;
		}
		
		System.out.println(day);

	}

	public static void dfs(int[][] arr, ArrayList<Point> arrayList, int i, int j, boolean[][] visited, int L, int R) {
		visited[i][j] = true;
		arrayList.add(new Point(i, j));

		// 위로
		if (i - 1 >= 0 && !visited[i - 1][j] && L <= Math.abs(arr[i][j] - arr[i - 1][j])
				&& Math.abs(arr[i][j] - arr[i - 1][j]) <= R)
			dfs(arr, arrayList, i - 1, j, visited, L, R);

		// 왼쪽
		if (i + 1 < arr.length && !visited[i + 1][j] && L <= Math.abs(arr[i][j] - arr[i + 1][j])
				&& Math.abs(arr[i][j] - arr[i + 1][j]) <= R)
			dfs(arr, arrayList, i + 1, j, visited, L, R);

		// 왼쪽
		if (j - 1 >= 0 && !visited[i][j - 1] && L <= Math.abs(arr[i][j] - arr[i][j - 1])
				&& Math.abs(arr[i][j] - arr[i][j - 1]) <= R)
			dfs(arr, arrayList, i, j - 1, visited, L, R);

		// 오른쪽
		if (j + 1 < arr[0].length && !visited[i][j + 1] && L <= Math.abs(arr[i][j] - arr[i][j + 1])
				&& Math.abs(arr[i][j] - arr[i][j + 1]) <= R)
			dfs(arr, arrayList, i, j + 1, visited, L, R);
	}
}
