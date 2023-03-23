import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N, M;
	public static int[][] arr;
	public static boolean[][] visited;
	public static int MAXCNT = 0;
	public static int MAXSUM = 0;
	
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	
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
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] != 0) {
					visited = new boolean[N][M];
					bfs(i, j);
				}
			}
		}
		
		System.out.println(MAXSUM);
	}
	
	public static void bfs(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i, j, 1));
		visited[i][j] = true;
		
		int maxcnt=0;
		int lastnum=0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			int ii = node.i;
			int jj = node.j;
			int cnt = node.cnt;
			
			if(cnt > maxcnt) {
				maxcnt = cnt;
				lastnum = arr[ii][jj];
			}
			else if(cnt == maxcnt) {
				if(arr[ii][jj] > lastnum) {
					lastnum = arr[ii][jj];
				}
			}
			
			for(int p=0; p<4; p++) {
				int iii = ii + nr[p];
				int jjj = jj + nc[p];
				
				if(iii>=0 && iii<N && jjj>=0 && jjj<M && !visited[iii][jjj] && arr[iii][jjj]!=0) {
					visited[iii][jjj] = true;
					queue.add(new Node(iii, jjj, cnt+1));
				}
			}
		}
		
		if(maxcnt > MAXCNT) {
			MAXCNT = maxcnt;
			MAXSUM = arr[i][j] + lastnum;
		}
		else if(maxcnt == MAXCNT) {
			MAXSUM = Math.max(MAXSUM, arr[i][j] + lastnum);
		}
	}

}