import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		PriorityQueue<Long> pQ = new PriorityQueue<>();

		int n, m;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			pQ.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i = 0 ; i < m ; i++) {
			long v1 = pQ.remove();
			long v2 = pQ.remove();
			pQ.add(v1+v2);
			pQ.add(v1+v2);
		}
		
		long sum = 0;
		for(long tmp : pQ)
			sum += tmp;
		
		System.out.println(sum);

	}
}