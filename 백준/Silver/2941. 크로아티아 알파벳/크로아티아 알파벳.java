import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		s = s.replace("c=", "X");
		s = s.replace("c-", "X");
		s = s.replace("dz=", "X");
		s = s.replace("d-", "X");
		s = s.replace("lj", "X");
		s = s.replace("nj", "X");
		s = s.replace("s=", "X");
		s = s.replace("z=", "X");

		System.out.println(s.length());

	}

}
