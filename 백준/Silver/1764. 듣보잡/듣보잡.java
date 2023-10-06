import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		TreeMap<String, Boolean> map = new TreeMap<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			map.put(br.readLine().trim(), false);
		}
		int cnt = 0;

		for (int i = 0; i < M; i++) {
			String s = br.readLine().trim();
			if (map.get(s) != null) {
				map.put(s, true);
				cnt++;
			}
		}
		for (Entry<String, Boolean> entry : map.entrySet()) { // 저장된 key값 확인
			if (entry.getValue()) {
				sb.append(entry.getKey()).append("\n");
			}

		}
		System.out.println(cnt);

		System.out.println(sb);

	}

}