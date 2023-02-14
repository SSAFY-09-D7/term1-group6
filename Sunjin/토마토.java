package algorithm;

import java.io.*;
import java.util.*;

class Point{
	int row, col, count;

	public Point(int row, int col, int count) {
		super();
		this.row = row;
		this.col = col;
		this.count = count;
	}
}


public class Main {
	static int M, N;
	static int[][] map;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static boolean[][] visited;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		result = 0;
		
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.add(new Point(i, j, 0));
					visited[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int nextRow = p.row + dRow[i];
				int nextCol = p.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				
				if(map[nextRow][nextCol] != -1 && !visited[nextRow][nextCol]) {
					q.add(new Point(nextRow, nextCol, p.count + 1));
					visited[nextRow][nextCol] = true;
					map[nextRow][nextCol] = 1;
				}	
			}
			result = Math.max(result, p.count);
			
		}	
		
		boolean flag = true;
		L:for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					flag = false;
					break L;
				}
			}
		}
		
		if(!flag) System.out.println(-1);
		else System.out.println(result);
		
	}	
}
