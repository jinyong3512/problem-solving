import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

public class Main { // 메인 클래스

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		int L, C;
		char[] arr;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			arr[i] = st.nextToken().charAt(0);

		//////////////////////////////////////////

		Arrays.sort(arr);

		recursion(L, 0, arr, new String(""), -1, sb);
		
		System.out.println(sb);

	}

	public static void recursion(int L, int depth, char[] arr, String tmp, int last_pick, StringBuilder sb) {
		if (depth == L) {
			int consonant_num = 0;
			int vowel_num = 0;
			for (int i = 0; i < tmp.length(); i++) {
				if (tmp.charAt(i) == 'a' || tmp.charAt(i) == 'e' || tmp.charAt(i) == 'i' || tmp.charAt(i) == 'o'
						|| tmp.charAt(i) == 'u')
					consonant_num++;
				else
					vowel_num++;
			}

			if (consonant_num >= 1 && vowel_num >= 2)
				sb.append(tmp).append("\n");
		}

		for (int i = last_pick + 1; i < arr.length; i++) {
			tmp += arr[i];
			recursion(L, depth + 1, arr, tmp, i, sb);
			tmp = tmp.substring(0, tmp.length() - 1);
		}
	}
}