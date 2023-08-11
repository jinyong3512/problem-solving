import java.util.*; // 유틸 라이브러리 임포트
import java.io.*; // 입출력 라이브러리 임포트
import java.lang.reflect.Array;

// 

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

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += arr[i];
		}

		int no_my_kid_sum = sum - 100;

		int criminal_1 = -1;
		int criminal_2 = -1;

		for (int i = 0; i < 9; i++) {
			int no_my_kid_last_num = no_my_kid_sum - arr[i];
			for (int j = i + 1; j < 9; j++) {
				if (arr[j] == no_my_kid_last_num) {
					criminal_1 = i;
					criminal_2 = j;
				}
			}
		}
		
		for(int i =0 ; i< 9 ; i++) {
			if(i == criminal_1 || i == criminal_2)
				continue;
			else
				System.out.println(arr[i]);
		}

	}
}
