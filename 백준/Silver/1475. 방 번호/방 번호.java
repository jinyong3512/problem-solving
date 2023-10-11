import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int ans = 0;
		char[] num = br.readLine().trim().toCharArray();
		int[] used = new int[10];
		for (int i = 0; i < num.length; i++) {
			int n = num[i] - '0';
			if (n == 6 || n == 9) {
				if (used[6] > used[9]) {
					used[9]++;
					ans = Math.max(ans, used[9]);
				} else {
					used[6]++;
					ans = Math.max(ans, used[6]);
				}
			} else {
				used[n]++;
				ans = Math.max(ans, used[n]);
			}

		}

		System.out.println(ans);

	}

}