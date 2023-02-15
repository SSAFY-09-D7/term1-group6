package algorithm;

import java.io.*;
import java.util.*;


public class Solution{
	static int N;
	static int[][] map;
	static boolean[][] dateMap;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	
	static class Point{
		int row, col;
		public Point(int row, int col){
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
			
			map = new int[N][N];
			int dayMax = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(dayMax < map[i][j]) dayMax = map[i][j];
				}
			}
			
			int max = 1;
			
			// 하루하루 지나감
			for(int day = 1; day <= dayMax; day++) {
				// 날짜별 지도 그리기 true는 먹힌 것
				drawMap(day);
				// 덩어리 개수 찾기
				int value = find();
				if(max < value) max = value;
			}
			
			System.out.printf("#%d %d\n", testCase, max);
		}
	}
	private static int find() {
		visited = new boolean[N][N];
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && !dateMap[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
		return count;
		
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
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
				
				if(!dateMap[nextRow][nextCol] && !visited[nextRow][nextCol]) {
					q.add(new Point(nextRow, nextCol));
					visited[nextRow][nextCol] = true;
				}
			}
		}
	}
	private static void drawMap(int day) {
		dateMap = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] <= day) dateMap[i][j] = true;
			}
		}
	}
}