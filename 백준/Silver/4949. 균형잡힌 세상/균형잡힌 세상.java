import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			String line = br.readLine();
			if (line.equals("."))
				break;

			Stack<Character> stack = new Stack<>();

			int i = 0;
			for (; i < line.length(); i++) {
				if (line.charAt(i) == '(' || line.charAt(i) == '[') {
					stack.push(line.charAt(i));
				} else if (line.charAt(i) == ')') {
					if (stack.isEmpty()) {

						break;
					} else {
						if (stack.peek() == '[') {

							break;
						} else if (stack.peek() == '(')
							stack.pop();
						else {
							System.out.println("SDFJSDKLFJDKLSFDKLS");
						}
					}

				} else if (line.charAt(i) == ']') {
					if (stack.isEmpty()) {

						break;
					} else {
						if (stack.peek() == '(') {

							break;
						} else if (stack.peek() == '[')
							stack.pop();
						else {
							System.out.println("SDFJSDKLFJDKLSFDKLS");
						}
					}
				}
			}

			if (i != line.length() || !stack.isEmpty())
				sb.append("no").append("\n");
			else
				sb.append("yes").append("\n");

		}
		System.out.println(sb);

	}
}
