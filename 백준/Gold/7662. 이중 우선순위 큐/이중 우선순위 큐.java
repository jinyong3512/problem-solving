import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			PriorityQueue<Integer> max_pQ = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2)
						return -1;
					else if (o2 == o1)
						return 0;
					else
						return 1;

				}
			});
			PriorityQueue<Integer> min_pQ = new PriorityQueue<>();
			HashMap<Integer, Integer> hashMap = new HashMap<>();
			int max_index = -1;
			int min_index = -1;

			int k = Integer.parseInt(br.readLine());
			for (int time = 0; time < k; time++) {
				st = new StringTokenizer(br.readLine());
				if (st.nextToken().equals("I")) {
					int number = Integer.parseInt(st.nextToken());
					hashMap.put(number, hashMap.getOrDefault(number, 0) + 1);
					min_pQ.add(number);
					max_pQ.add(number);
				} else {
					int order = Integer.parseInt(st.nextToken());
					if (hashMap.size() == 0)
						continue;
					if (order == 1) {
						while (!hashMap.containsKey(max_pQ.peek())) {
							max_pQ.remove();
						}
						int number = max_pQ.remove();

						if (hashMap.get(number) == 1)
							hashMap.remove(number);
						else
							hashMap.put(number, hashMap.get(number) - 1);

					} else {
						while (!hashMap.containsKey(min_pQ.peek())) {
							min_pQ.remove();
						}
						int number = min_pQ.remove();

						if (hashMap.get(number) == 1)
							hashMap.remove(number);
						else
							hashMap.put(number, hashMap.get(number) - 1);
					}

				}
			}

			if (hashMap.size() == 0)
				System.out.println("EMPTY");
			else {
				while (!hashMap.containsKey(max_pQ.peek())) {
					max_pQ.remove();
				}
				int max_number = max_pQ.remove();

				while (!hashMap.containsKey(min_pQ.peek())) {
					min_pQ.remove();
				}
				int min_number = min_pQ.remove();

				System.out.println(max_number + " " + min_number);
			}

		}

	}
}