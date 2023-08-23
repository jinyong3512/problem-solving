import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

//

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N, d, k, c;
		int[] arr;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		///////////////////////////////////////////////
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		hashMap.put(c, 1);

		int answer = 0;
		int left = 0;
		int right = 0;

		for (; right < k; right++)
			hashMap.put(arr[right], hashMap.getOrDefault(arr[right], 0) + 1);

		for (; left < N; left++) {
			if (hashMap.get(arr[left]) == 1)
				hashMap.remove(arr[left]);
			else {
				hashMap.put(arr[left], hashMap.get(arr[left]) - 1);
			}

			hashMap.put(arr[right % N], hashMap.getOrDefault(arr[right % N], 0) + 1);
			right++;

			answer = Math.max(answer, hashMap.size());
		}

		System.out.println(answer);
	}
}