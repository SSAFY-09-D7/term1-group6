import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] map;
	static char[][] smap;
	static boolean[][] visited;
	static boolean[][] sVisited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	
	static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        map = new char[N][N];
        smap = new char[N][N];
        for(int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for(int j = 0; j < N; j++) {
        		map[i][j] = str.charAt(j);
        		smap[i][j] = str.charAt(j);
        		if(smap[i][j] == 'R') smap[i][j] = 'G';
        	}
        }
        
        visited = new boolean[N][N];
        sVisited = new boolean[N][N];
        int cnt = 0;
        int sCnt = 0;
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(!visited[i][j]) {
        			bfs(i, j, map[i][j], visited, map);
        			cnt++;
        		}
        		if(!sVisited[i][j]) {
        			bfs(i, j, smap[i][j], sVisited, smap);
        			sCnt++;
        		}
        	}
        }
        
        System.out.println(cnt + " " + sCnt);
        
	}
	private static void bfs(int row, int col, char ch, boolean[][] v, char[][] m) {
		Queue<Point> q = new LinkedList<>();
		
		v[row][col] = true;
		q.add(new Point(row, col));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int nextRow = p.row + dRow[i];
				int nextCol = p.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
				
				if(!v[nextRow][nextCol] && m[nextRow][nextCol] == ch) {
					v[nextRow][nextCol] = true;
					q.add(new Point(nextRow, nextCol));
				}
			}
		}
		
	}
}