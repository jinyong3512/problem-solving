import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			int N, K;
			String line;

			///////////////////////////////////////////////

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			line = br.readLine();

			///////////////////////////////////////////////

			HashMap<String, Boolean> hashMap = new HashMap<>();

			for (int i = 0; i < N / 4; i++) {

				// N/4 만큼씩 끊어 읽을 꺼임 총 4번
				hashMap.put(line.substring(0, N / 4), true);
				hashMap.put(line.substring(N / 4, N / 4 * 2), true);
				hashMap.put(line.substring(N / 4 * 2, N / 4 * 3), true);
				hashMap.put(line.substring(N / 4 * 3, N / 4 * 4), true);

				line = line.charAt(line.length() - 1) + line.substring(0, line.length() - 1);

			}

			ArrayList<Integer> arrayList = new ArrayList<>();
			for (String key : hashMap.keySet()) {
//				System.out.println("key  = " + key);
				int tmp = 0;

				for (int i = 0; i < key.length(); i++) {
					int value;
					if ('A' <= key.charAt(i) && key.charAt(i) <= 'F') {
						value = key.charAt(i) - '0' - 7;
					} else {
						value = key.charAt(i) - '0';
					}
					tmp += value * Math.pow(16, key.length() - 1 - i);
				}
//				System.out.println("tmp = " + tmp);
				arrayList.add(tmp);
			}
//			System.out.println();

			Collections.sort(arrayList,Collections.reverseOrder());

			sb.append("#").append(test_case).append(" ").append(arrayList.get(K - 1)).append("\n");

		}
		System.out.println(sb);

	}
}