import java.io.*;
import java.util.*;
// 아기상어 2
public class Main {
	static int N, M;
	static int max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
	static class Point{
		int row, col, depth;
		public Point(int row, int col, int depth) {
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}
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
		
		max = Integer.MIN_VALUE;	
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					visited = new boolean[N][M];
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);
	}
	private static void bfs(int row, int col) {
		Queue<Point> q = new LinkedList<>();
		visited[row][col] = true;
		q.add(new Point(row, col, 0));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(map[now.row][now.col]== 1) {
				if(max < now.depth) max = now.depth;	
				break;
			}
			
			for(int i = 0; i < 8; i++) {
				int nextRow = now.row + dRow[i];
				int nextCol = now.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				
				if(!visited[nextRow][nextCol]) {
					visited[nextRow][nextCol] = true;
					q.add(new Point(nextRow, nextCol, now.depth + 1));
				}
			}
		}
		
	}

}
