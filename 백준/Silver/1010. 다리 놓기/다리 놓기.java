import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트
import java.math.BigInteger;

//

public class Main { // 메인 클래스
	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N, M;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 서쪽이 N 오른쪽이 M N<=M
			// MCN
			BigInteger son = new BigInteger("1");
			BigInteger mother = new BigInteger("1");
			for (int i = M; i >= M - (N - 1); i--) {
				son = son.multiply(BigInteger.valueOf(i));
			}
			for (int i = N; i >= 1; i--) {
				mother = mother.multiply(BigInteger.valueOf(i));
			}

			sb.append(son.divide(mother)).append("\n");
		}
		System.out.println(sb);

	}
}