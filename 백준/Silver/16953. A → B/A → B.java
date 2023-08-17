import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int A, B;

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		/////////////////////////////////////////
		int count = 0;
		while (B > A) {
			count++;

			if (B % 10 == 1) {
				B = B / 10;
			} else if (B % 2 == 0) {
				B = B / 2;
			} else
				break;

		}

		if (B == A)
			System.out.println(count + 1);
		else
			System.out.println("-1");

	}
}