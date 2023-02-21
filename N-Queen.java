package algorithm;

import java.io.*;
import java.util.*;

public class Main2 {
	static int N;
	static int[][] map;
	static int count;
	public static void main(String[] args) throws IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];

    	solve(0);
    	System.out.println(count);
	}
	
	/*
	 * Queen : 1
	 * blank : 0
	 */
	private static void solve(int rowCount) {
		// basis part
		if(rowCount == N) {
			count++;
			return;
		}
		
		// inductive part
		for(int col = 0; col < N; col++) {
			if(check(rowCount, col)) {
				map[rowCount][col] = 1;
				solve(rowCount + 1);
				map[rowCount][col] = 0;	
			}
		}
	}

	// 퀸을 놓는 시점에 row, col 위치의 상, 상좌, 상우에는 퀸이 없어야 함
	private static boolean check(int row, int col) {
		for(int i = row - 1; i >= 0; i--) {
			if(map[i][col] == 1) return false;
		}
		
		for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--,j--) {
			if(map[i][j] == 1) return false;
		}
		
		for(int i = row -1, j = col + 1; i >= 0 && j < N; i--, j++) {
			if(map[i][j] == 1) return false;
		}
		
		return true;
		
	}
}