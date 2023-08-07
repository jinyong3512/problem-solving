import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int new_score;
		int P;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		new_score = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		arr = new int[N];

		if (N == 0) {
			System.out.println("1");
		} else {
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			////////////////////////////////////////////////
			boolean update = false;
			if (N == P) {
				for (int i = 0; i < N; i++) {
					if (arr[i] < new_score)
						update = true;
				}
			} else {
				update = true;
			}

			if (update) {
				int i = 0;
				for (; i < N; i++) {
					if (arr[i] <= new_score)
						break;
				}
				System.out.println(i+1);
			} else {
				System.out.println("-1");
			}
		}

	}

}