import java.io.*;
import java.util.*;

class Data {
	String value;

	Data(String value) {
		this.value = value;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();

		//////////////////////////////////////

		ArrayList<ArrayList<Data>> done = new ArrayList<>();
		ArrayList<ArrayList<Data>> yet = new ArrayList<>();
		ArrayList<Data> datas;

		datas = new ArrayList<>();
		datas.add(new Data(String.valueOf(line.charAt(0))));
		yet.add(datas);

		for (int i = 1; i < line.length(); i++) {
			String order = String.valueOf(line.charAt(i));
			i++;
			String number = String.valueOf(line.charAt(i));

			ArrayList<ArrayList<Data>> new_done = new ArrayList<>();
			ArrayList<ArrayList<Data>> new_yet = new ArrayList<>();

			for (ArrayList<Data> tmp : done) {

				ArrayList<Data> new_tmp = new ArrayList<>();
				for (int j = 0; j < tmp.size(); j++) {
					new_tmp.add(new Data(tmp.get(j).value));
				}

				new_tmp.add(new Data(order));
				new_tmp.add(new Data(number));
				new_yet.add(new_tmp);
			}
			for (ArrayList<Data> tmp : yet) {
				ArrayList<Data> new_tmp = new ArrayList<>();
				for (int j = 0; j < tmp.size(); j++) {
					new_tmp.add(new Data(tmp.get(j).value));
				}

				new_tmp.add(new Data(order));
				new_tmp.add(new Data(number));
				new_yet.add(new_tmp);

				new_tmp = new ArrayList<>();
				for (int j = 0; j < tmp.size(); j++) {
					new_tmp.add(new Data(tmp.get(j).value));
				}

				Data data = new_tmp.remove(tmp.size() - 1);
				if (order.equals("+")) {
					new_tmp.add(new Data(String.valueOf(Integer.parseInt(data.value) + Integer.parseInt(number))));
				} else if (order.equals("-")) {
					new_tmp.add(new Data(String.valueOf(Integer.parseInt(data.value) - Integer.parseInt(number))));
				} else if (order.equals("*")) {
					new_tmp.add(new Data(String.valueOf(Integer.parseInt(data.value) * Integer.parseInt(number))));
				}
				new_done.add(new_tmp);

			}

			done = new_done;
			yet = new_yet;

		}

//		for (ArrayList<Data> tmp : yet) {
//			for (Data data : tmp)
//				System.out.print(data.value);
//			System.out.println();
//		}
//		for (ArrayList<Data> tmp : done) {
//			for (Data data : tmp)
//				System.out.print(data.value);
//			System.out.println();
//		}

		int answer = Integer.MIN_VALUE;

		for (ArrayList<Data> tmp : yet) {
			int answer_candidate = Integer.parseInt(tmp.get(0).value);
			for (int i = 1; i < tmp.size(); i++) {
				String order = tmp.get(i).value;
				i++;
				String number = tmp.get(i).value;
				
				if (order.equals("+")) {
					answer_candidate += Integer.parseInt(number);
				} else if (order.equals("-")) {
					answer_candidate -= Integer.parseInt(number);
				} else if (order.equals("*")) {
					answer_candidate *= Integer.parseInt(number);
				}
			}
			
			answer = Math.max(answer,answer_candidate);

		}
		for (ArrayList<Data> tmp : done) {
			int answer_candidate = Integer.parseInt(tmp.get(0).value);
			for (int i = 1; i < tmp.size(); i++) {
				String order = tmp.get(i).value;
				i++;
				String number = tmp.get(i).value;
				
				if (order.equals("+")) {
					answer_candidate += Integer.parseInt(number);
				} else if (order.equals("-")) {
					answer_candidate -= Integer.parseInt(number);
				} else if (order.equals("*")) {
					answer_candidate *= Integer.parseInt(number);
				}
			}
			
			answer = Math.max(answer,answer_candidate);
		}
		
		System.out.println(answer);

	}
}