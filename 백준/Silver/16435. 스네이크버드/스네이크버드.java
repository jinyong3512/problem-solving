import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int N,L;
		int[] heights;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		heights = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i =0 ; i < N ; i++)
			heights[i] = Integer.parseInt(st.nextToken());
		
		//////////////////////////////////////////////////
		
		Arrays.sort(heights);
		
		for(int i =0 ; i < N ; i++) {
			if(heights[i] <= L) {
				L++;
			}
			else {
				break;
			}
		}
		
		System.out.println(L);

	}
}
