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

	static int N;
	
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	
	static class Node{
		int i, j;
		
		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void bfs(int i, int j, int day) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int p=0; p<4; p++) {
				int ii = node.i + r[p];
				int jj = node.j + c[p];
				
				if(ii>=0 && ii<N && jj>=0 && jj<N && !visited[ii][jj] && arr[ii][jj]>day) {
					visited[ii][jj] = true;
					queue.add(new Node(ii, jj));
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
			
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N][N];
			//visited = new boolean[N][N];
			int max_day = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(max_day < arr[i][j])
						max_day = arr[i][j];
				}
			}
			
			int max_cnt = 0;
			int cnt = 0;
			
			for(int day=0; day<=max_day; day++) {
				cnt = 0;
				visited = new boolean[N][N];
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(arr[i][j] > day && !visited[i][j]) {
							bfs(i, j, day);
							cnt++;
						}
					}
				}
				if(cnt > max_cnt)
					max_cnt = cnt;
			}
			System.out.printf("#%d %d\n", test_case, max_cnt);
		}
	}
}