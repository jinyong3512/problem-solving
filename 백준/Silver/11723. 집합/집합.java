import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int M;
		M = Integer.parseInt(br.readLine());

		//////////////////////////////////////////

		HashMap<Integer, Boolean> hashMap = new HashMap<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();

			if (order.equals("add"))
				hashMap.put(Integer.parseInt(st.nextToken()), true);
			else if (order.equals("remove"))
				hashMap.remove(Integer.parseInt(st.nextToken()));
			else if (order.equals("check")) {
				int number = Integer.parseInt(st.nextToken());
				if (hashMap.containsKey(number))
					sb.append("1").append("\n");
				else
					sb.append("0").append("\n");
			} else if (order.equals("toggle")) {
				int number = Integer.parseInt(st.nextToken());
				if (hashMap.containsKey(number))
					hashMap.remove(number);
				else
					hashMap.put(number, true);
			} else if (order.equals("all")) {
				for (int j = 1; j <= 20; j++) {
					hashMap.put(j, true);
				}
			} else {
				hashMap.clear();
			}
		}
		
		System.out.println(sb);

	}
}
