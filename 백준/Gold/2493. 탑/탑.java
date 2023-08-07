import java.io.*;
import java.util.*;

class Data {
	int height;
	int index;

	Data(int height, int index) {
		this.height = height;
		this.index = index;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		int[] heights;

		N = Integer.parseInt(br.readLine());
		heights = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			heights[i] = Integer.parseInt(st.nextToken());

		///////////////////////////////////////////////////

		int[] answer = new int[N];
		Stack<Data> stack = new Stack<>();

		for (int i = N - 1; i >= 0; i--) {
			if (stack.isEmpty())
				stack.push(new Data(heights[i],i));
			else {
				// 새 친구가 키가 작아
				if (heights[i] < stack.peek().height) {
					stack.push(new Data(heights[i],i));
				}
				// 새 친구가 키가 같거나 커
				else {
					while (!stack.isEmpty() && heights[i] >= stack.peek().height) {
						answer[stack.peek().index] = i+1;
						stack.pop();
					}
					stack.push(new Data(heights[i],i));
				}
			}
		}
		
		for(int tmp  : answer)
			System.out.print(tmp+" ");

	}

}
