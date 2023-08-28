import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			int m, c, i;
			int[] memories;
			char[] codes;
			char[] inputs;

			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());

			memories = new int[m];
			codes = new char[c];
			inputs = new char[i];

			String line = br.readLine();
			for (int index = 0; index < c; index++) {
				codes[index] = line.charAt(index);
			}

			line = br.readLine();
			for (int index = 0; index < i; index++) {
				inputs[index] = line.charAt(index);
			}

			/////////////////////////////////////////////
			HashMap<Integer, Integer> hashMap = new HashMap<>();
			Stack<Integer> stack = new Stack<>();
			for (int codes_index = 0; codes_index < c; codes_index++) {
				if (codes[codes_index] == '[')
					stack.add(codes_index);
				else if (codes[codes_index] == ']') {
					int tmp = stack.pop();
					hashMap.put(tmp, codes_index);
					hashMap.put(codes_index, tmp);
				}
			}

			int pointer = 0;
			int codes_index = 0;
			int inputs_index = 0;
			int count = 0;
			int count2 = 0;

			boolean loop = false;
			boolean[] loops = new boolean[codes.length];

			while (true) {
				if (codes[codes_index] == '-') {
					memories[pointer]--;
					if (memories[pointer] == -1)
						memories[pointer] = 255;
				} else if (codes[codes_index] == '+') {
					memories[pointer]++;
					if (memories[pointer] == 256)
						memories[pointer] = 0;
				} else if (codes[codes_index] == '<') {
					pointer--;
					if (pointer == -1)
						pointer = memories.length - 1;
				} else if (codes[codes_index] == '>') {
					pointer++;
					if (pointer == memories.length)
						pointer = 0;
				} else if (codes[codes_index] == '[') {
					if (memories[pointer] == 0) {
						codes_index = hashMap.get(codes_index);
					}

				} else if (codes[codes_index] == ']') {
					if (memories[pointer] != 0) {
						codes_index = hashMap.get(codes_index);
					}

				} else if (codes[codes_index] == '.') {

				} else if (codes[codes_index] == ',') {
					if (inputs_index < i) {
						memories[pointer] = inputs[inputs_index];
						inputs_index++;
					} else {
						memories[pointer] = 255;
					}
				}
				codes_index++;
				count++;

				if (codes_index >= c)
					break;

				if (count >= 50000000)
					loop = true;

				if (loop) {
					loops[codes_index] = true;
					count2++;
				}

				if (count2 >= 50000000)
					break;

			}

			if (loop) {
				sb.append("Loops").append(" ");
				for (int index = 0; index < loops.length; index++) {
					if (loops[index]) {
						sb.append(index - 1).append(" ");
						break;
					}
				}
				for (int index = loops.length - 1; index >= 0; index--) {
					if (loops[index]) {
						sb.append(index).append(" ");
						break;
					}
				}
				sb.append("\n");

			} else {
				sb.append("Terminates").append("\n");
			}

		}

		System.out.println(sb);

	}

}