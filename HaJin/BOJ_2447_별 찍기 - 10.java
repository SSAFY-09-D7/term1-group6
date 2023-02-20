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
		if(n == 1)
			return;
		
		for(int p=i+n/3; p<i+n/3*2; p++) {
			for(int q=j+n/3; q<j+n/3*2; q++) {
				arr[p][q] = ' ';
			}
		}
		
		for(int p=0; p<3; p++) {
			for(int q=0; q<3; q++) {
				func(i+n/3*p, j+n/3*q, n/3);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		arr = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = '*';
			}
		}
		
		func(0, 0, N);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
    
}