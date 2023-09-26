import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[][] arr = new int[9][9];

		for (int i = 0; i < 9; i++) {
			String input_line = br.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = input_line.charAt(j) - '0';
			}
		}

		//////////////////////////////////////////////////////

		// 가로
		boolean[][] i_visited = new boolean[9][10];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				i_visited[i][arr[i][j]] = true;
			}
		}
		// 세로
		boolean[][] j_visited = new boolean[9][10];
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				j_visited[j][arr[i][j]] = true;
			}
		}

		// 사각형
		boolean[][][] visited = new boolean[3][3][10];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						visited[i][j][arr[3 * i + y][3 * j + x]] = true;
					}
				}
			}
		}

		recursion(arr, i_visited, j_visited, visited, 0);
	}

	public static void recursion(int[][] arr, boolean[][] i_visited, boolean[][] j_visited, boolean[][][] visited,
			int index) {
		if (index == 81) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println("");
			}
			System.exit(0);
		}

		if (arr[index / 9][index % 9] == 0) {
			for (int num = 1; num <= 9; num++) {
				if (!i_visited[index / 9][num] && !j_visited[index % 9][num]
						&& !visited[index / 9 / 3][index % 9 / 3][num]) {
					i_visited[index / 9][num] = true;
					j_visited[index % 9][num] = true;
					visited[index / 9 / 3][index % 9 / 3][num] = true;
					arr[index / 9][index % 9] = num;
					recursion(arr, i_visited, j_visited, visited, index + 1);
					i_visited[index / 9][num] = false;
					j_visited[index % 9][num] = false;
					visited[index / 9 / 3][index % 9 / 3][num] = false;
					arr[index / 9][index % 9] = 0;
				}
			}
		}
		else {
			recursion(arr, i_visited, j_visited, visited, index + 1);
		}
	}
}