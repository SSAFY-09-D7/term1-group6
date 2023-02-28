package algorithm;

import java.io.*;
import java.util.*;

public class Solution{
	static int N, M, R, C, L;
	static int count;
	static int[][] map;
	static boolean[][] visited;
	static class Info{
		int row, col, value, time;
		public Info(int row, int col, int value, int time) {
			this.row = row;
			this.col = col;
			this.value = value;
			this.time = time;
		}
	}
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	
	static int[][] 	list = { {1, 2, 5, 6},
			 				 {1, 2, 4, 7},
			 				 {1, 3, 4, 5},
			 				 {1, 3, 6, 7}}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			count = 0;
			bfs(R, C);
			System.out.println("#" + testCase + " " + count);
		}
	}

	private static void bfs(int row, int col) {
		Queue<Info> q = new LinkedList<>();
		
		visited[row][col] = true;
		q.add(new Info(row, col, map[row][col], 1));
		
		while(!q.isEmpty()) {
			Info now = q.poll();
			int curValue = now.value;
			count++;
			
			for(int i = 0; i < 4; i++) {
				int nextRow = now.row + dRow[i];
				int nextCol = now.col + dCol[i];
				
				if(now.value == 2 && (i==2 || i==3)) {
	                continue;
	            }
	            else if(now.value == 3 && (i==0 || i==1)) {
	                continue;
	            }
	            else if(now.value == 4 && (i==1 || i==2)) {
	                continue;
	            }
	            else if(now.value == 5 && (i==0 || i==2)) {
	                continue;
	            }
	            else if(now.value == 6 && (i==0 || i==3)) {
	                continue;
	            }
	            else if(now.value == 7 && (i==1 || i==3))
	                continue;
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				
				// 미방문지점이며 아직 이동 가능한 시간이라면
				if(!visited[nextRow][nextCol] && now.time < L) {
					for(int j = 0; j < 4; j++) {
						int nextValue = list[i][j];
						if(map[nextRow][nextCol] == nextValue) {
							visited[nextRow][nextCol] = true;
							q.add(new Info(nextRow, nextCol, map[nextRow][nextCol], now.time + 1));
						}
					}
				}
			}
		}
	}
}