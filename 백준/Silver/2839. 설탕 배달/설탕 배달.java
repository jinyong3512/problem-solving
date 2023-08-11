import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;

		N = Integer.parseInt(br.readLine());

		///////////////////////////////////////////////////

		int answer = -1;

		for (int i = N / 5; i >= 0; i--) {
			int tmp = N - 5 * i;

			if (tmp % 3 == 0) {
				answer = i + tmp / 3;
				break;
			}

		}
		System.out.println(answer);

	}
}
