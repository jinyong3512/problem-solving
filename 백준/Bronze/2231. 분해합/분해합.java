import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int ans = 0;
		for (int i = 1; i < N; i++) {
			if (sum(i + "") == N) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}

	public static int sum(String i) {
		int tmp = 0;
		for (int k = 0; k < i.length(); k++) {
			tmp += (i.charAt(k) - '0');
		}
		return tmp+Integer.parseInt(i);
	}

}
