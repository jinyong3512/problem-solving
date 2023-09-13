import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N, A, B;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		///////////////////////////////////////////////////

		int answer = 0;
		while (true) {
			answer++;

			A = (A + 1) / 2;
			B = (B + 1) / 2;

			if (A == B)
				break;

		}

		System.out.println(answer);
	}
}