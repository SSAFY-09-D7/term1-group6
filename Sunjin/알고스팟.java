import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	static int M, N;
	static int result;
	static int[][] map;
	static int[][] distance;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static class Info implements Comparable<Info>{
		int row, col, breakCount;
		public Info(int row, int col, int breakCount) {
			this.row = row;
			this.col = col;
			this.breakCount = breakCount;
		}
		@Override
		public int compareTo(Main.Info o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.breakCount, o.breakCount);
		}
	}
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for(int j = 0; j < M; j++) {
        		map[i][j] = str.charAt(j) - '0';
        	}
        }

        dijkstra(0, 0);
        System.out.println(result);
    }
	private static void dijkstra(int row, int col) {
		PriorityQueue<Info> q = new PriorityQueue();
		q.add(new Info(row, col, 0));
		
		distance = new int[N][M];
		for(int i = 0; i < N; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		distance[0][0] = 0;
		
		while(!q.isEmpty()) {
			Info now = q.poll();
			
			if(now.row == N - 1 && now.col == M - 1) {
				result = now.breakCount;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nextRow = now.row + dRow[i];
				int nextCol = now.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				
				int value = 0;
				if(map[nextRow][nextCol] == 1) value = 1;
				
				if(distance[now.row][now.col] + value < distance[nextRow][nextCol]) {
					distance[nextRow][nextCol] = distance[now.row][now.col] + value; 
					q.add(new Info(nextRow, nextCol, distance[nextRow][nextCol]));
				}
			}
		}
	}
}