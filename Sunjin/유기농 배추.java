package algorithm;

import java.io.*;
import java.util.*;

class Point{
	int row, col;

	public Point(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}


public class Main {
	static int M, N, K;
	static int[][] map;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
	
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 0; testCase < T; testCase++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int c = 0; c < K; c++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				map[Y][X] = 1;
			}
			
			int count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}

	private static void bfs(int row, int col) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(row, col));
		visited[row][col] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int nextRow = p.row + dRow[i];
				int nextCol = p.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				
				if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
					q.add(new Point(nextRow, nextCol));
					visited[nextRow][nextCol] = true;
				}
			}
		}
		
	}
}
