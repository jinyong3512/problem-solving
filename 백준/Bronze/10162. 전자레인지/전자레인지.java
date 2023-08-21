import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string_input = br.readLine();

        int int_input = Integer.parseInt(string_input);

        int a = int_input/300;
        int_input %= 300;

        int b = int_input/60;
        int_input %= 60;

        int c = int_input/10;
        int_input %= 10;

        if (int_input ==0)
            System.out.printf("%d %d %d\n",a,b,c);
        else
            System.out.println("-1");

    }
}