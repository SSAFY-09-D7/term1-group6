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

public class Solution{
	
	StringBuilder sb = new StringBuilder();
	static int N, M;
	
	static char[][] arr;
	static int[][] devil_visited;
	static int[][] sooyeon_visited;
	
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	
	static int devil_i, devil_j;
	static int sooyeon_i, sooyeon_j;
	static int god_i, god_j;
	
	static int ans;
	
	
	static class Node{
		int i, j, cnt;
		
		public Node(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}

	public static void devil_bfs(int i, int j, int cnt) {
		Queue<Node> queue = new LinkedList<>();
		
		//int rcnt = cnt;
		queue.add(new Node(i, j, cnt));
		if(devil_visited[i][j] == 0 || devil_visited[i][j] < cnt)
			devil_visited[i][j] = cnt;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int p=0; p<4; p++) {
				int ii = node.i + r[p];
				int jj = node.j + c[p];
				
				if(ii>=0 && ii<N && jj>=0 && jj<M && (arr[ii][jj] == 'S' || arr[ii][jj] == '.' || arr[ii][jj] == '*') && (devil_visited[ii][jj] == 0 || devil_visited[ii][jj] > node.cnt+1)) {
					
					devil_visited[ii][jj] = node.cnt + 1;
					queue.add(new Node(ii, jj, node.cnt + 1));
					
						
				}
			}
		}
	}
	
	public static void sooyeon_bfs(int i, int j, int cnt) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(i, j, cnt));
		sooyeon_visited[i][j] = cnt;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.i == god_i && node.j == god_j) {
				if(node.cnt < ans)
					ans = node.cnt;
			}
			
			
			for(int p=0; p<4; p++) {
				int ii = node.i + r[p];
				int jj = node.j + c[p];
				
				if(ii>=0 && ii<N && jj>=0 && jj<M && (arr[ii][jj] == '.' || arr[ii][jj] == 'D') && sooyeon_visited[ii][jj] == 0 && (devil_visited[ii][jj] > node.cnt+1 || devil_visited[ii][jj] == 0)) {
					sooyeon_visited[ii][jj] = node.cnt + 1;
					queue.add(new Node(ii, jj, node.cnt + 1));
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new char[N][M];
			devil_visited = new int[N][M];
			sooyeon_visited = new int[N][M];
			
			ans = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<M; j++) {
					arr[i][j] = s.charAt(j);

					if(arr[i][j] == 'S') {
						sooyeon_i = i;
						sooyeon_j = j;
					}
					else if(arr[i][j] == 'D') {
						god_i = i;
						god_j = j;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == '*')
						devil_bfs(i, j, 1);
				}
			}
            
			sooyeon_bfs(sooyeon_i, sooyeon_j, 1);			
			
			if(ans == Integer.MAX_VALUE)
				System.out.printf("#%d GAME OVER\n", test_case);
			else
				System.out.printf("#%d %d\n", test_case, ans-1);
		}

	}
}