import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H;
	static int min = Integer.MAX_VALUE;
	static int[][][] map;
	static boolean[][][] visited;
	static int[] dRow = {0, 0, -1, 1, 0, 0};
	static int[] dCol = {0, 0, 0, 0, -1, 1};
	static int[] dH = {-1, 1, 0, 0, 0, 0};
	static class Point{
		int row, col, h, depth;
		public Point(int h, int row, int col, int depth) {
			this.h = h;
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		Queue<Point> q = new LinkedList();
		
		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if(map[h][i][j] == 1) {
						q.add(new Point(h, i, j, 0));
						visited[h][i][j] = true;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			min = cur.depth;
			for(int i = 0; i < 6; i++) {
				int nextH = cur.h + dH[i];
				int nextRow = cur.row + dRow[i];
				int nextCol = cur.col + dCol[i];
				
				if(nextH < 0 || nextRow < 0 || nextCol < 0 || nextH >= H || nextRow >= N || nextCol >= M) continue;
				
				if(!visited[nextH][nextRow][nextCol] && map[nextH][nextRow][nextCol] != -1) {
					map[nextH][nextRow][nextCol] = 1;
					visited[nextH][nextRow][nextCol] = true;
					q.add(new Point(nextH, nextRow, nextCol, cur.depth + 1));
				}
			}
		}
		
		if(check()) System.out.println(min);
		else System.out.println(-1);
	}
	
	private static boolean check() {
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[h][i][j] == 0) return false;
				}
			}
		}
		return true;
	}
}
