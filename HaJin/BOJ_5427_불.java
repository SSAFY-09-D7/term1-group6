import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int T, W, H;
	public static char[][] arr;
	public static int[][] fire;
	public static boolean[][] visited;
	public static int sangX, sangY;
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	public static Queue<Node> queue;
	public static int MIN;;
	public static class Node{
		int i;
		int j;
		int cnt;
		Node(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=0; test_case<T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			arr = new char[H][W];
			queue = new LinkedList<>();
			visited = new boolean[H][W];
			MIN = Integer.MAX_VALUE;
			fire = new int[H][W];
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					fire[i][j] = -1;
				}
			}
			
			for(int i=0; i<H; i++) {
				String s = br.readLine();
				for(int j=0; j<W; j++) {
					arr[i][j] = s.charAt(j);
					
					if(arr[i][j] == '@') {
						sangX = i;
						sangY = j;
						arr[i][j] = '.';
					}
					else if(arr[i][j] == '*') {
						queue.add(new Node(i, j, 0));
						visited[i][j] = true;
						fire[i][j] = 0;
					}
				}
			}
			
			fireBFS();
			
			visited = new boolean[H][W];
			sangBFS();
			
			if(MIN == Integer.MAX_VALUE) {
				System.out.println("IMPOSSIBLE");
			}
			else
				System.out.println(MIN+1);
		}
		
	}
	
	public static void fireBFS() {
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;
			
			for(int p=0; p<4; p++) {
				int ii = i + nr[p];
				int jj = j + nc[p];
				
				if(ii>=0 && ii<H && jj>=0 && jj<W && !visited[ii][jj] && arr[ii][jj]=='.') {
					visited[ii][jj] = true;
					fire[ii][jj] = cnt + 1;
					queue.add(new Node(ii, jj, cnt+1));
				}
			}
		}
		
	}
	
	public static void sangBFS() {
		queue = new LinkedList<>();
		visited[sangX][sangY] = true;
		queue.add(new Node(sangX, sangY, 0));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;
			
			if(i==0 || i==H-1 || j==0 || j==W-1) {
				if(MIN > cnt) {
					MIN = cnt;
					break;
				}
			}
			
			for(int p=0; p<4; p++) {
				int ii = i + nr[p];
				int jj = j + nc[p];
				
				if(ii>=0 && ii<H && jj>=0 && jj<W && !visited[ii][jj] && arr[ii][jj]=='.' && (fire[ii][jj]==-1 || fire[ii][jj]>cnt+1)){
					visited[ii][jj] = true;
					queue.add(new Node(ii, jj, cnt+1));
				}
			}
		}
	}

}