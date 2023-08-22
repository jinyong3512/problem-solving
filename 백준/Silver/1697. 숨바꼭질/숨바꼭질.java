import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N, K;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		/////////////////////////////////////////////////////

		ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
		boolean[] visited = new boolean[1000001];
		arrayList.add(new ArrayList<Integer>());
		arrayList.get(0).add(N);
		visited[N] = true;

		int find = -1;
		while (true) {
			ArrayList<Integer> new_time = new ArrayList<>();

			for (int tmp : arrayList.get(arrayList.size() - 1)) {

				if (tmp == K) {
					find = arrayList.size() - 1;
					break;
				}

				if (tmp - 1 >= 0 && !visited[tmp - 1]) {
					visited[tmp - 1] = true;
					new_time.add(tmp - 1);
				}
				if (tmp + 1 <= 1000000 && !visited[tmp + 1]) {
					visited[tmp + 1] = true;
					new_time.add(tmp + 1);
				}
				if (2 * tmp <= 1000000 && !visited[2 * tmp]) {
					visited[2 * tmp] = true;
					new_time.add(2 * tmp);
				}

			}

			if (find != -1)
				break;

			arrayList.add(new_time);

		}

		System.out.println(find);

	}

}
