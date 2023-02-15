package algorithm;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Solution{
	static int N;
	static int count;
	static int[][] map;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxCount = 0;
			int[][] result = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					count = 0;
					visited = new boolean[N][N];
					dfs(i, j);
					result[i][j] = count;
					if(maxCount <= count) maxCount = count;
				}
			}
			
			ArrayList<Integer> list = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(result[i][j] == maxCount) {
						list.add(map[i][j]);
					}
				}
			}
			
			Collections.sort(list);
			
			System.out.printf("#%d %d %d\n",testCase, list.get(0), maxCount);
		}
	}
	private static void dfs(int row, int col) {
		count++;
		visited[row][col] = true;
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
			
			if(map[row][col] + 1 == map[nextRow][nextCol] && !visited[nextRow][nextCol]) {
				dfs(nextRow, nextCol);
				visited[nextRow][nextCol] = false;
			}
		}
	}

}