import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	
	public static int T, N;
	public static int startX, startY;
	public static int endX, endY;
	public static int[] nr = {-2, -2, -1, -1, 1, 1, 2, 2};
	public static int[] nc = {-1, 1, -2, 2, -2, 2, -1, 1};
	public static int ANS;
	public static boolean[][] visited;
	
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
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			ANS = Integer.MAX_VALUE;
			visited = new boolean[N][N];
			
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			bfs();
			
			System.out.println(ANS);
		}
		
	}
	
	public static void bfs() {
		
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			
			Node node = queue.poll();
			int ii = node.i;
			int jj = node.j;
			int cntcnt = node.cnt;
			
			if(ii == endX && jj == endY) {
				if(ANS > cntcnt)
					ANS = cntcnt;
			}
			
			for(int p=0; p<8; p++) {
				int iii = ii + nr[p];
				int jjj = jj + nc[p];
				
				if(iii>=0 && iii<N && jjj>=0 && jjj<N &&!visited[iii][jjj]) {
					visited[iii][jjj] = true;
					queue.add(new Node(iii, jjj, cntcnt+1));
				}
			}
			
		}
		
	}
}