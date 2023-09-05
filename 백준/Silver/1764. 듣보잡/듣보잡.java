import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		///////////////////////////////////////

		HashMap<String, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < N + M; i++) {
			String input_line = br.readLine();
			hashMap.put(input_line, hashMap.getOrDefault(input_line, 0) + 1);
		}

		ArrayList<String> arrayList = new ArrayList<>();

		for (String key : hashMap.keySet()) {
			if(hashMap.get(key)==2)
				arrayList.add(key);
		}
		
		Collections.sort(arrayList);
		
		System.out.println(arrayList.size());
		for(String str : arrayList)
			System.out.println(str);
	}
}