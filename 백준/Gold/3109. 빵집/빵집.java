import java.io.*;
import java.util.*;

// 

class Main {
	public static int answer = 0;
	public static boolean find;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int R, C;
		String[][] arr;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new String[R][C];

		for (int i = 0; i < R; i++) {
			String input_line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = String.valueOf(input_line.charAt(j));
			}
		}

		/////////////////////////////////////////

		boolean[][] visited = new boolean[R][C];
		for (int start_i = R - 1; start_i >= 0; start_i--) {
			find = false;
			recursion(R, C, arr, visited, start_i, 0);
		}

		System.out.println(answer);

	}

	public static void recursion(int R, int C, String[][] arr, boolean[][] visited, int y, int x) {
		if (x == C - 1) {
			answer++;
			find = true;
			return;
		}

		// 오른쪽 아래로 가자!
		if (y + 1 < R && x + 1 < C && !arr[y + 1][x + 1].contentEquals("x") && !visited[y + 1][x + 1]) {
			visited[y + 1][x + 1] = true;
			recursion(R, C, arr, visited, y + 1, x + 1);
			if(find)
				return;
		}

		// 오른쪽으로 가자!!
		if (x + 1 < C && !arr[y][x + 1].contentEquals("x") && !visited[y][x + 1]) {
			visited[y][x + 1] = true;
			recursion(R, C, arr, visited, y, x + 1);
			if(find)
				return;
		}

		// 오른쪽 위로 가자!
		if (y - 1 >= 0 && x + 1 < C && !arr[y - 1][x + 1].contentEquals("x") && !visited[y - 1][x + 1]) {
			visited[y - 1][x + 1] = true;
			recursion(R, C, arr, visited, y - 1, x + 1);
			if(find)
				return;
		}

	}

}
