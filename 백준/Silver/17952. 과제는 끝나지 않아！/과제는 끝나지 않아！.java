import java.io.*; // 입출력 라이브러리 임포트
import java.util.*; // 유틸 라이브러리 임포트

class Data { // 업무를 클래스로 정의하자
	int A, T; // A는 점수 T는 시간

	Data(int A, int T) { // 생성자
		this.A = A; // A= A;
		this.T = T; // T = T;
	}
}

public class Main { // 문제 2번 클레스
	public static void main(String[] args) throws IOException { // 메인 함수 실행
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 객체 초기화 할당
		StringBuilder sb = new StringBuilder(); // 출력 객체 초기화 할당
		StringTokenizer st; // 끊어 읽기 객체 초기화

		int N = Integer.parseInt(br.readLine()); // 한줄 읽고 정수형으로 바꿔서 N으로 만들기
		int[][] arr = new int[N][3]; // 업무 정보 배열

		for (int i = 0; i < N; i++) { // N번돌아
			st = new StringTokenizer(br.readLine()); // 한줄 읽어
			arr[i][0] = Integer.parseInt(st.nextToken()); // 끊어 읽기 처음꺼
			if (arr[i][0] == 1) { // 처음꺼 1이면 추가 정보 받아
				arr[i][1] = Integer.parseInt(st.nextToken()); // 점수야
				arr[i][2] = Integer.parseInt(st.nextToken()); // 시간이야
			}
		}

		////////////////////////////////////////////////////

		Stack<Data> stack = new Stack<>(); // 하던 업무를 저장해야 하므로 stack을 쓰자

		int answer = 0; // 점수 초기는 0

		for (int i = 0; i < N; i++) { // N번 돌아
			if (arr[i][0] == 1) { // 새 임무가 있다면
				arr[i][2]--; // 새임무 1분 빼

				if (arr[i][2] == 0) { // 끝냈다!
					answer += arr[i][1]; // 점수 흭득
				} else { // 못끝냄 ㅠ
					stack.add(new Data(arr[i][1], arr[i][2])); // 스택에 넣어놔~ 나중에 하게
				}
			} else { // 새 임무 없음
				if (!stack.isEmpty()) { // 스택이 안비었으면 일 하자 비었으면 노는시간이야
					Data tmp = stack.pop(); // 가장 최근의 일 꺼내
					tmp.T--; // 1분 일해봐
					if (tmp.T == 0) { // 끝냈다!
						answer += tmp.A; // 점수 흭득
					} else { // 못 끝냄ㅠ
						stack.add(tmp); // 다시 스택에 넣자 나중에 일하게
					}
				}
			}
		}

		System.out.println(answer);
	}
}