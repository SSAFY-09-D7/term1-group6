import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	public static int N;

	public static int[] arr = {1,3,7,9};
	
	public static void func(int n, int k) {
		
		if(k+1 == N)
			System.out.println(n);
		
		int now = n;
		boolean flag = false;
		for(int i=0; i<4; i++) {
			now = n*10 + arr[i];
			
			flag = false;
			for(int j=2; j<now/2; j++) {
				if(now % j ==0) {
					flag = true;
					break;
				}
			}
			
			if(!flag)
				func(now, k+1);
		}	
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;

		N = Integer.parseInt(br.readLine());
		
		func(2, 0);
		func(3, 0);
		func(5, 0);
		func(7, 0);
	}
}

