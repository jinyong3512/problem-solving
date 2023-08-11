import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트

// 	14084	120

public class Main { // 메인 클래스

	public static void main(String[] args) throws IOException { // 메인 클래스 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받는 라이브러리 객체 생성 초기화
		StringBuilder sb = new StringBuilder(); // 출력 하는 라이브러리 객체 생성 초기화
		StringTokenizer st; // 끊어 읽는 용도 라이브러리 객체 생성

		// 난쟁이 수로 초기화 한다 모자속에 숨은 숫자를
		int[] arr = new int[9];

		// 한칸씩 9 번 입력 받아! 한줄을 정수형으로 바꿔
		for (int i = 0; i < 9; i++)
			arr[i] = Integer.parseInt(br.readLine());

		///////////////////////////////////////////////////////

		// 9명 모두의 합을 구하자
		int sum = 0;
		for (int i = 0; i < 9; i++) { // 9번 돌아
			sum += arr[i]; // 한 숫자씩 더해
		}

		// 내 아이가 아닌 2명의 숫자 합은
		int no_my_kid_sum = sum - 100;

		// 범인인놈들 인덱스 2개
		int criminal_1 = -1;
		int criminal_2 = -1;

		// O(N^2)으로 범인 찾자
		for (int i = 0; i < 9; i++) { // 내 후보 하나 선택
			int no_my_kid_last_num = no_my_kid_sum - arr[i]; // 선택했으니 빼주면 나머지 한명의 값이 나온다 
			for (int j = i + 1; j < 9; j++) { // 선택한 애 뒤로 선택해보기
				if (arr[j] == no_my_kid_last_num) { // 어 찾았다 범인
					criminal_1 = i;	// 1번 범인 i놈
					criminal_2 = j; // 2번 범인 j놈
				}
			}
		}
		
		// 9번 돌며 출력하자
		for(int i =0 ; i< 9 ; i++) {
			// 범인 인덱스면 패스
			if(i == criminal_1 || i == criminal_2)
				continue;
			// 내아이면 출력
			else
				System.out.println(arr[i]);
		}

	}
}
