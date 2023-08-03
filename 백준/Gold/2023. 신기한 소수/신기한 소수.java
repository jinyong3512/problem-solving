// 14180KB 492ms

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		//////////////////////////////////////////////////////

		ArrayList<ArrayList<Integer>> big_arrayList = new ArrayList<>();
		ArrayList<Integer> small_arrayList;

		small_arrayList = new ArrayList<>();
		small_arrayList.add(2);
		small_arrayList.add(3);
		small_arrayList.add(5);
		small_arrayList.add(7);

		big_arrayList.add(small_arrayList);

		for (int i = 1; i < N; i++) {
			small_arrayList = new ArrayList<>();

			for (int tmp : big_arrayList.get(i - 1)) {
				// 1 3 7 9

				int new_number;
				new_number = tmp * 10 + 1;
				if (check(new_number))
					small_arrayList.add(new_number);

				new_number = tmp * 10 + 3;
				if (check(new_number))
					small_arrayList.add(new_number);

				new_number = tmp * 10 + 7;
				if (check(new_number))
					small_arrayList.add(new_number);

				new_number = tmp * 10 + 9;
				if (check(new_number))
					small_arrayList.add(new_number);

			}

			big_arrayList.add(small_arrayList);
		}

		for (int tmp : big_arrayList.get(N - 1))
			sb.append(tmp).append("\n");
		System.out.println(sb);
	}

	public static boolean check(int number) {
		for (int i = 2; i*i < number; i++) {
			if (number % i == 0)
				return false;
		}

		return true;
	}
}