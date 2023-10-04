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

		recursion(arr, 0);
	}

	public static void recursion(int[][] arr, int index) {
		if (index == 81) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		if (arr[index / arr.length][index % arr.length] == 0) {
			for (int i = 1; i <= 9; i++) {
				boolean can = true;

				for (int y = 0; y < 9; y++) {
					if (arr[y][index % arr.length] == i)
						can = false;
				}

				for (int x = 0; x < 9; x++) {
					if (arr[index / arr.length][x] == i)
						can = false;
				}

				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						if (arr[index / arr.length / 3 * 3 + y][index % arr.length / 3 * 3 + x] == i)
							can = false;
					}
				}

				if (can) {
					arr[index / arr.length][index % arr.length] = i;
					recursion(arr, index + 1);
					arr[index / arr.length][index % arr.length] = 0;
				}

			}
		} else {
			recursion(arr, index + 1);
		}
	}
}