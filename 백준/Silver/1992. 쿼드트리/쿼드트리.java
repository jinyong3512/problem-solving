import java.io.*;
import java.util.*;

//

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 도우미
		StringBuilder sb = new StringBuilder(); // 출력 도우미
		StringTokenizer st; // 끊어 읽기 도우미

		int N = Integer.parseInt(br.readLine());
		String[][] arr = new String[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = String.valueOf(line.charAt(j));
			}
		}

		///////////////////////////////////////////////////////

		recursion(N, arr, sb, 0, 0);
		System.out.println(sb);

	}

	public static void recursion(int N, String[][] arr, StringBuilder sb, int i, int j) {
		if (judgement(N, arr, i, j)) {
			sb.append(arr[i][j]);
			return;
		} else {
			// 왼쪽 위
			sb.append("(");
			recursion(N / 2, arr, sb, i, j);

			// 오른쪽 위

			recursion(N / 2, arr, sb, i, j + N / 2);

			// 왼쪽 아래

			recursion(N / 2, arr, sb, i + N / 2, j);

			// 오른쪽 아래

			recursion(N / 2, arr, sb, i + N / 2, j + N / 2);
			sb.append(")");
		}
	}

	public static boolean judgement(int N, String[][] arr, int i, int j) {

		String init = arr[i][j];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (!arr[i+y][j+x].equals(init))
					return false;
			}
		}
		return true;
	}
}
