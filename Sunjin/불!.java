import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] map;
	static Queue<Point> q;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static boolean[][][] visited;
	static ArrayList<Point> fireList;
	static class Point{
		int row, col, depth;
		char value;
		public Point(int row, int col, char value, int depth) {
			this.row = row;
			this.col = col;
			this.value = value;
			this.depth = depth;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		q = new LinkedList<>();
		fireList = new ArrayList<>();
		visited = new boolean[R][C][2];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') {
					visited[i][j][0] = true;
					q.add(new Point(i, j, map[i][j], 0));
				}
				else if(map[i][j] == 'F') {
					visited[i][j][1] = true;
					fireList.add(new Point(i, j, map[i][j], 0));
				}
			}
		}
		
		for(int i = 0; i < fireList.size(); i++) {
			q.add(new Point(fireList.get(i).row, fireList.get(i).col, 'F', 0));
		}
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.value == 'J') {
				if(map[now.row][now.col] == 'F') continue;
				for(int i = 0; i < 4; i++) {
					int nextRow = now.row + dRow[i];
					int nextCol = now.col + dCol[i];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) {
						System.out.println(now.depth + 1);
						System.exit(0);
					}
					
					// 방문한 곳이면 굳이 안감
					if(visited[nextRow][nextCol][0]) continue;
					
					// 벽이거나 불이면 못감
					if(map[nextRow][nextCol] == '#' || map[nextRow][nextCol] == 'F') continue;
					
					visited[nextRow][nextCol][0] = true;
					q.add(new Point(nextRow, nextCol, 'J', now.depth + 1));
				}
			}
			
			else if(now.value == 'F') {
				for(int i = 0; i < 4; i++) {
					int nextRow = now.row + dRow[i];
					int nextCol = now.col + dCol[i];
					
					// 범위 벗어나면 못 감
					if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
					
					// 방문한 곳이면 굳이 안감
					if(visited[nextRow][nextCol][1]) continue;
					
					// 벽이면 못 감
					if(map[nextRow][nextCol] == '#') continue;
				
					map[nextRow][nextCol] = 'F';
					visited[nextRow][nextCol][1] = true;
					q.add(new Point(nextRow, nextCol, 'F', now.depth + 1));
				}
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}
}