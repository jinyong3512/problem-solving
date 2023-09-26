import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		System.out.println(pow(a, b, c));

	}

	public static long pow(long a, long e, long c) {
		if (e == 1) {
			return a % c;
		}
		long temp = pow(a, e / 2, c);
		if (e % 2 == 1) {
			return (temp * temp % c) * a % c;
		}
		return temp * temp % c;

	}

}