import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int M;
		boolean[] breakdown = new boolean[10];

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				breakdown[Integer.parseInt(st.nextToken())] = true;
		}

		/////////////////////////////////////////////////////

		int min = -1;
		int max = -1;

		int num;
		for (num = N; num >= 0; num--) {
			boolean can = true;

			String tmp = String.valueOf(num);
			for (int i = 0; i < tmp.length(); i++) {
				if (breakdown[tmp.charAt(i) - '0'])
					can = false;
			}
			if (can) {
				min = num;
				break;
			}
		}

		for (num = N; num <= 1000000; num++) {
			boolean can = true;

			String tmp = String.valueOf(num);
			for (int i = 0; i < tmp.length(); i++) {
				if (breakdown[tmp.charAt(i) - '0'])
					can = false;
			}
			if (can) {
				max = num;
				break;
			}
		}

		int min_answer;
		int max_answer;

		if (min != -1)
			min_answer = String.valueOf(min).length() + N - min;
		else {
			min_answer = Integer.MAX_VALUE;
		}
		if (max != -1)
			max_answer = String.valueOf(max).length() + max - N;
		else {
			max_answer = Integer.MAX_VALUE;
		}
		int directly_answer = Math.abs(100 - N);

		System.out.println(Math.min(Math.min(min_answer, max_answer), directly_answer));

	}

}
