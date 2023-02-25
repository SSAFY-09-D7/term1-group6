package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static int min;
	static class Point{
		int row, col, idx;
		public Point(int row, int col, int idx) {
			this.row = row;
			this.col = col;
			this.idx = idx;
		}
	}
	
	static class PointD{
		int row, col, depth;
		public PointD(int row, int col, int depth) {
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        visited = new boolean[N][N];
        int idx = 1;
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(!visited[i][j] && map[i][j] == 1) {
        			findIslandNum(i, j, idx);
        			idx++;
        		}
        	}
        }
        
        min = Integer.MAX_VALUE;
        
        for(int s = 1; s <= idx; s++) {
        	for(int i = 0; i < N; i++) {
            	for(int j = 0; j < N; j++) {
            		if(map[i][j] == s) {
            			visited = new boolean[N][N];
            			findBridge(i, j, s, 0);
            		}
            	}
            }
        }
        
        if(min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
	
	private static void findBridge(int row, int col, int idx, int depth) {
		Queue<PointD> q = new LinkedList<>();
		
		visited[row][col] = true;
		q.add(new PointD(row, col, depth));
		
		while(!q.isEmpty()) {
			PointD cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextRow = cur.row + dRow[i];
				int nextCol = cur.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
				
				if(map[nextRow][nextCol] == idx || map[nextRow][nextCol] == 0) {
					if(!visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						q.add(new PointD(nextRow, nextCol, cur.depth + 1));
					}
				}
				else {
					if(!visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
					}
					if(cur.depth < min) min = cur.depth;
				}
			}
		}
	}
	
	private static void findIslandNum(int row, int col, int idx) {
		Queue<Point> q = new LinkedList<>();
		
		visited[row][col] = true;
		map[row][col] = idx;
		q.add(new Point(row, col, idx));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nextRow = cur.row + dRow[i];
				int nextCol = cur.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
				
				if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
					visited[nextRow][nextCol] = true;
					map[nextRow][nextCol] = cur.idx;
					q.add(new Point(nextRow, nextCol, cur.idx));
				}
			}
		}
		
	}
}