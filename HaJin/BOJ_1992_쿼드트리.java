import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[][] arr;	
	
	public static void func(int i, int j, int n) {
		if(n == 1) {
			if(arr[i][j] == '1')
				sb.append('1');
			else
				sb.append('0');
			
			
			return;
		}
		
		int one = 0;
		int zero = 0;
		
		for(int p=i; p<i+n; p++) {
			for(int q=j; q<j+n; q++) {
				if(arr[p][q] == '0')
					zero += 1;
				else
					one += 1;
			}
		}
		
		if(zero == n*n)
			sb.append('0');
		else if(one == n*n)
			sb.append('1');
		else {
			sb.append('(');
			func(i, j, n/2);
			func(i, j+n/2, n/2);
			func(i+n/2, j, n/2);
			func(i+n/2, j+n/2, n/2);
			sb.append(')');
		}
		
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		arr = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		func(0, 0, N);
		
		System.out.println(sb);

	}
}