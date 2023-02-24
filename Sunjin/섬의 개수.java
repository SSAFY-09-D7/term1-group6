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
	static int w, h;
	static int[][] map;
	static int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) break;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			sb.append(count + "\n");
		}
		System.out.println(sb);
		
	}

	private static void bfs(int row, int col) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(row, col));
		visited[row][col] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 8; i++) {
				int nextRow = p.row + dRow[i];
				int nextCol = p.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= h || nextCol >= w) continue;
				
				if(map[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
					q.add(new Point(nextRow, nextCol));
					visited[nextRow][nextCol] = true;
				}
			}
		}
	}
}
