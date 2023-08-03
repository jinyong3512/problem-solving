// 

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int S, P;
		String str;
		int[] need_num = new int[4];
		int[] have_num = new int[4];

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		str = br.readLine();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++)
			need_num[i] = Integer.parseInt(st.nextToken());

		//////////////////////////////////////////////////////

		int answer = 0;

		int left_index = 0;
		int right_index = 0;
		for (; right_index < P; right_index++) {
			if (str.charAt(right_index) == 'A')
				have_num[0]++;
			else if (str.charAt(right_index) == 'C')
				have_num[1]++;
			else if (str.charAt(right_index) == 'G')
				have_num[2]++;
			else
				have_num[3]++;
		}
		right_index--;

		while (true) {
			boolean success = true;
			for (int i = 0; i < 4; i++) {
				if (need_num[i] > have_num[i])
					success = false;
			}
			if (success)
				answer++;

			if (str.charAt(left_index) == 'A')
				have_num[0]--;
			else if (str.charAt(left_index) == 'C')
				have_num[1]--;
			else if (str.charAt(left_index) == 'G')
				have_num[2]--;
			else
				have_num[3]--;

			left_index++;

			right_index++;
			if (right_index >= S)
				break;

			if (str.charAt(right_index) == 'A')
				have_num[0]++;
			else if (str.charAt(right_index) == 'C')
				have_num[1]++;
			else if (str.charAt(right_index) == 'G')
				have_num[2]++;
			else
				have_num[3]++;
		}

		System.out.println(answer);

	}
}