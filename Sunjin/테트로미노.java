import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int sum;
	static int max;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
	}
	private static void dfs(int row, int col, int depth, int tmpSum) {
		if(depth == 4) {
			if(max < tmpSum) max = tmpSum;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M || visited[nextRow][nextCol]) continue;
			
			if(depth == 2) {
				visited[nextRow][nextCol] = true;
				dfs(row, col, depth + 1, tmpSum + map[nextRow][nextCol]);
			}
			
			visited[nextRow][nextCol] = true;
			dfs(nextRow, nextCol, depth + 1, tmpSum + map[nextRow][nextCol]);
			visited[nextRow][nextCol] = false;
		}
		
	}
}