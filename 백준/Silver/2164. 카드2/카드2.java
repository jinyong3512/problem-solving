import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++)
			queue.add(i);

		while (queue.size() != 1) {
			queue.remove();
			queue.add(queue.remove());
		}
		
		System.out.println(queue.remove());

	}
}