import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static char[] arr;
	public static int N;
	
	public static void func(int start, int end) {
		if(end - start == 1) {
			return;
		}
		
		for(int i=start+(end-start)/3; i< end-(end-start)/3; i++) {
			arr[i] = ' ';
		}
		
		func(start, start+(end-start)/3);
		func(end-(end-start)/3, end);
		
	}

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;

		while((input = br.readLine()) != null) {
            
			N = Integer.parseInt(input);
			
			double a = Math.pow(3, N);
			
			arr = new char[(int)a];
			for(int i=0; i<arr.length; i++)
				arr[i] = '-';

			func(0, (int)a);
			
			for(char c : arr)
				bw.append(c);
			bw.append("\n");
		}
		bw.flush();
	}
}
