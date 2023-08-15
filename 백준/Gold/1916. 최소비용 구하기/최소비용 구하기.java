import java.io.*;
import java.util.*;

class Data {
	int index;
	long fee;

	Data(int index, long fee) {
		this.index = index;
		this.fee = fee;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M;
		ArrayList<ArrayList<Data>> graph = new ArrayList<>();
		int start;
		int end;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++) {
			ArrayList<Data> arrayList = new ArrayList<>();
			graph.add(arrayList);
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph.get(Integer.parseInt(st.nextToken()))
					.add(new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		/////////////////////////////////////////////////////////////////

		Long[] d = new Long[N + 1];
		for (int i = 1; i <= N; i++)
			d[i] = Long.MAX_VALUE;
		d[start] = 0L;

		PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (d[o1] > d[o2])
					return 1;
				else if (d[o1] == d[o2])
					return 0;
				else
					return -1;
			}
		});

		pQ.add(start);

		while (!pQ.isEmpty()) {
			Integer tmp = pQ.remove();
			
			if(pQ.contains(tmp))
				continue;

			for (int i = 0; i < graph.get(tmp).size(); i++) {
				// d[다음꺼] vs d[현재나] + 다음꺼 fee
				if (d[graph.get(tmp).get(i).index] > d[tmp] + graph.get(tmp).get(i).fee) {
					d[graph.get(tmp).get(i).index] = d[tmp] + graph.get(tmp).get(i).fee;
					pQ.add(graph.get(tmp).get(i).index);
				}

			}

		}

		System.out.println(d[end]);
	}

}