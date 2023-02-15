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
	
	public static int N;
	public static boolean[][] visited;
	public static int[][] arr;
	
	public static int[] a = {-1, 1, 0, 0};
	public static int[] b = {0, 0, -1, 1};
	
	public static ArrayList<Integer> ans = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j=0; j<N; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		int cntcnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				if(arr[i][j] == 1 && !visited[i][j]) {
					cntcnt += 1;
					bfs(i, j, 1);
				}
		}
		
		Collections.sort(ans);
		
		System.out.println(cntcnt);
		
		for(int i=0; i<ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}
	
	public static void bfs(int i, int j, int cnt) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int x = p.x;
			int y = p.y;
			
			for(int l=0; l<4; l++) {
				int xx = x + a[l];
				int yy = y + b[l];
				
				if(xx>=0 && xx<N && yy>=0 && yy<N && !visited[xx][yy] && arr[xx][yy]==1) {
					visited[xx][yy] = true;
					queue.add(new Point(xx, yy));
					cnt+=1;
				}
			}
		}
		ans.add(cnt);
	}
	
	static class Point{
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}