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
	
	public static int N, M;
	public static boolean[][] visited;
	public static int[][] arr;
	
	public static int[] a = {-1, 1, 0, 0};
	public static int[] b = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case=0; test_case<T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			arr = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int ta = Integer.parseInt(st.nextToken());
				int tb = Integer.parseInt(st.nextToken());
				arr[tb][ta] = 1;
			}
			
			int ans = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 1 && !visited[i][j]) {
						ans += 1;
						bfs(i, j);
					}
				}
			}
			System.out.println(ans);
		}
	}
	
	public static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int x = p.x;
			int y = p.y;
			
			for(int k=0; k<4; k++) {
				int xx = x + a[k];
				int yy = y + b[k];
				
				if(xx>=0 && xx<N && yy>=0 && yy<M && !visited[xx][yy] && arr[xx][yy]==1) {
					visited[xx][yy] = true;
					queue.add(new Point(xx, yy));
				}
			}
		}
	}
	
	static class Point{
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}