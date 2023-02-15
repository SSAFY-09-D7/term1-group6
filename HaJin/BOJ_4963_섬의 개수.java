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
	
	public static int w, h;
	public static boolean[][] visited;
	public static int[][] arr;
	
	public static int[] a = {-1, -1, -1, 1, 1, 1, 0, 0, 0};
	public static int[] b = {-1, 0, 1, -1, 0, 1, -1, 1, 0};
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w==0 && h==0)
				break;
			
			arr = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = 0;
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
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
			
			for(int k=0; k<8; k++) {
				int xx = x + a[k];
				int yy = y + b[k];
				
				if(xx>=0 && xx<h && yy>=0 && yy<w && !visited[xx][yy] && arr[xx][yy]==1) {
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