import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	public static int M, N;
	public static boolean[][] visited;
	public static int[][] arr;
	public static int[][] cnt;
	
	public static int[] a = {-1, 1, 0, 0};
	public static int[] b = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		cnt = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		int ans = 0;
		
		L : for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!visited[i][j] && arr[i][j] == 0) {
						ans = -1;
						break L;
					}
					else {
						ans = Math.max(cnt[i][j], ans);
					}
				}
			}
		
		System.out.println(ans);
	}
	
	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					queue.add(new Point(i, j, 0));
					visited[i][j] = true;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int x = p.x;
			int y = p.y;
			
			for(int i=0; i<4; i++) {
				int xx = x + a[i];
				int yy = y + b[i];
				
				if(xx>=0 && yy>=0 && xx<N && yy<M && !visited[xx][yy] && arr[xx][yy] == 0) {
					visited[xx][yy] = true;
					queue.add(new Point(xx, yy, p.cnt + 1));
					cnt[xx][yy] = p.cnt + 1;
				}
			}
		}
	}
	
	static class Point{
		int x, y, cnt;
		
		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
