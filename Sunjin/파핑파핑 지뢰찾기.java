package algorithm;

import java.io.*;
import java.util.*;

public class Solution{
	static int N;
	static char[][] cMap;
	static int[][] map;
	static int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
	static boolean[][] visited;
	
	static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			cMap = new char[N][N];
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				cMap[i] = str.toCharArray();
				for(int j = 0; j < N; j++) {
					if(cMap[i][j] == '*') map[i][j] = -1;
					else map[i][j] = 0;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != -1) {
						changeMapCount(i, j);
					}
				}
			}
			
			int result = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 0 && !visited[i][j]) {
						bfs(i, j);
						result++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != -1 && !visited[i][j]) {
						result++;
					}
				}
			}
			
			System.out.println("#" + testCase + " " + result);
		}
	}

	private static void bfs(int row, int col) {
		Queue<Point> q = new LinkedList<>();
		
		visited[row][col] = true;
		q.add(new Point(row, col));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int currentRow = p.row;
			int currentCol = p.col;
			
			for(int i = 0; i < 8; i++) {
				int nextRow = currentRow + dRow[i];
				int nextCol = currentCol + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
				
				if(!visited[nextRow][nextCol]) {
					visited[nextRow][nextCol] = true;
					if(map[nextRow][nextCol] == 0) {
						q.add(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}

	private static void changeMapCount(int row, int col) {
		int count = 0;
		for(int i = 0; i < 8; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
			
			if(map[nextRow][nextCol] == -1) count++;
		}
		map[row][col] = count;
	}
}