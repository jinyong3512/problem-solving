import java.util.*;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			String trash = sc.nextLine();
			String input_line = sc.nextLine();

			Queue<Integer> queue = new LinkedList<>();

			for (int i = 0; i < 8; i++)
				queue.add(Integer.parseInt(input_line.split(" ")[i]));

			while (true) {
				boolean stop = false;
				for (int i = 1; i <= 5; i++) {
					int tmp = queue.remove();
					tmp -= i;

					if (tmp <= 0) {
						queue.add(0);
						stop = true;
						break;
					} else {
						queue.add(tmp);
					}
				}

				if (stop)
					break;
			}

			System.out.print("#" + test_case + " ");
			for (int tmp : queue)
				System.out.print(tmp + " ");
			System.out.println("");

		}
	}
}