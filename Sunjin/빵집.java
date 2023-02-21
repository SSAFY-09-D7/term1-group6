package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int pipeCount;
	static char[][] map;
	static int[] dRow = {-1, 0, 1};
	static boolean[][] visited;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			char[] chs = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				map[i][j] = chs[j];
			}
		}
		
		for(int i = 0; i < R; i++) {
			flag = false;
			solve(i, 0);
		}
		
		System.out.println(pipeCount);
	}
	private static void solve(int row, int col) {
		if(col == C - 1) {
			pipeCount++;
			flag = true;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + 1;
			
			if(nextRow >= 0 && nextCol >= 0 && nextRow < R && nextCol < C 
					&& !flag && !visited[nextRow][nextCol] && map[nextRow][nextCol] == '.') {
				visited[nextRow][nextCol] = true;
				solve(nextRow, nextCol);
			}
		}
	}

}
