import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int result;
	static boolean[][] visited;
	static int[] dRow = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dCol = {-2, -1, 1, 2, -2, -1, 1, 2};
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
		
		T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int curRow = Integer.parseInt(st.nextToken());
			int curCol = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			Queue<Point> q = new LinkedList<>();
			visited[curRow][curCol] = true;
			q.add(new Point(curRow, curCol, 0));
			
			st = new StringTokenizer(br.readLine());
			int goRow = Integer.parseInt(st.nextToken());
			int goCol = Integer.parseInt(st.nextToken());
			
			result = 0;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				
				if(cur.row == goRow && cur.col == goCol) {
					result = cur.depth;
				}
				
				for(int d = 0; d < 8; d++) {
					int nextRow = cur.row + dRow[d];
					int nextCol = cur.col + dCol[d];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
					
					if(!visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						q.add(new Point(nextRow, nextCol, cur.depth + 1));
					}
				}
			}
			System.out.println(result);
		}
	}
}
