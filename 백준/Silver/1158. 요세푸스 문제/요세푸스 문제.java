import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, K;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ArrayList<Integer> arrayList = new ArrayList<>();
		
		for(int i = 1 ; i <= N ; i++)
			arrayList.add(i);
		
		int index =-1;
		
		sb.append("<");
		
		while(!arrayList.isEmpty()) {
			index += K;
			index = index%arrayList.size();
			sb.append(arrayList.remove(index)).append(",").append(" ");
			index--;
		}
		
		sb.setLength(sb.length()-2);
		sb.append(">");
		
		System.out.println(sb);

	}

}
