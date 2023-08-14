import java.io.*;
import java.util.*;

class Main {

	public static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, r, c;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		recursion(N, 0, 0, r, c);

		System.out.println(answer);

	}

	public static void recursion(int N, int i, int j, int r, int c) {
//		System.out.println("N = "+ N+" i = " + i + " j = " + j+" answer = " + answer);

		if (N == 0)
			return;

		// 위에 속함
		if (i <= r && r < i + (int) (Math.pow(2, N - 1))) {
			// 왼쪽에 속함
			if (j <= c && c < j + (int) (Math.pow(2, N - 1))) {
				recursion(N - 1, i, j, r, c);
			}
			// 오른쪽에 속함
			else {
				answer = (int) (answer + Math.pow(2, N - 1) * Math.pow(2, N - 1) * 1);
				recursion(N - 1, i, (int) (j + Math.pow(2, N - 1)), r, c);
			}
		}
		// 아래 속함
		else {
			// 왼쪽에 속함
			if (j <= c && c < j + (int) (Math.pow(2, N - 1))) {
				answer = (int) (answer + Math.pow(2, N - 1) * Math.pow(2, N - 1) * 2);
				recursion(N - 1, i + (int) (Math.pow(2, N - 1)), j, r, c);
			}
			// 오른쪽에 속함
			else {
				answer = (int) (answer + Math.pow(2, N - 1) * Math.pow(2, N - 1) * 3);
				recursion(N - 1, i + (int) (Math.pow(2, N - 1)), j + (int) (Math.pow(2, N - 1)), r, c);
			}

		}
	}
}