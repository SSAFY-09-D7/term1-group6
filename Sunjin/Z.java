package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int N, r, c;
	static int count;
	static int value;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
	
		goDeep(r, c, N);
		
	}
	private static void goDeep(int row, int col, int size) {
		if(size == 0) {
			System.out.println(value);
			return;
		}
		
		int len = (int)Math.pow(2, size);
		int half = len / 2;
		
		if(row < half && col < half) {
			goDeep(row, col, size - 1);
		}
		
		//2
		if(row < half && col >= half) {
			value += half * half;
			goDeep(row, col - half, size - 1);
		}
		
		//3
		if(row >= half && col < half) {
			value += len * half;
			goDeep(row - half, col, size - 1);
		}
		
		//4
		if(row >= half && col >= half) {
			value += len * half + half * half;
			goDeep(row - half, col - half , size - 1);
		}
	}
}
