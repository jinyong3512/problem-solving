import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		///////////////////////////////////////////////////
        
        Arrays.sort(arr);
        
        if(arr.length % 2 == 1)
            System.out.println(arr[arr.length/2]);
        else
            System.out.println(arr[arr.length/2-1]);

	}
}