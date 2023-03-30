import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int R, C;
	public static char[][] arr;
	public static boolean[][] visited;
	public static int now_x, now_y, end_x, end_y;
	public static int[][] waterTime;
	public static Queue<Node> queue;
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	public static int MIN = Integer.MAX_VALUE;
	
	public static class Node{
		int i; int j; int cnt;
		
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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		queue = new LinkedList<>();
		visited = new boolean[R][C];
		waterTime = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = s.charAt(j);
				
				if(arr[i][j] == 'D') {
					end_x = i;
					end_y = j;
				}
				else if(arr[i][j] == 'S') {
					now_x = i;
					now_y = j;
					arr[i][j] = '.';
				}
				else if(arr[i][j] == '*') {
					queue.add(new Node(i, j, 0));
					visited[i][j] = true;
					waterTime[i][j] = 0;
				}
			}
		}

		waterBFS();
	
		visited = new boolean[R][C];
		goBFS();

		if(MIN == Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else
			System.out.println(MIN);
	}
	
	public static void waterBFS() {
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int p=0; p<4; p++) {
				int ii = node.i + nr[p];
				int jj = node.j + nc[p];
				
				if(ii>=0 && ii<R && jj>=0 && jj<C && !visited[ii][jj] && arr[ii][jj]=='.') {
					visited[ii][jj] = true;
					waterTime[ii][jj] = node.cnt + 1;
					queue.add(new Node(ii, jj, node.cnt + 1));
				}
			}
		}
	}
	
	public static void goBFS() {
		queue.add(new Node(now_x, now_y, 0));
		visited[now_x][now_y] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.i == end_x && node.j == end_y) {
				MIN = node.cnt;
				break;
			}
			
			for(int p=0; p<4; p++) {
				int ii = node.i + nr[p];
				int jj = node.j + nc[p];
				
				if(ii>=0 && ii<R && jj>=0 && jj<C && !visited[ii][jj]) {
					if(arr[ii][jj] == 'D' || (arr[ii][jj]=='.' && (waterTime[ii][jj]>node.cnt+1 || waterTime[ii][jj]==0))) {
						visited[ii][jj] = true;
						queue.add(new Node(ii, jj, node.cnt+1));
					}
					
				}
			}
		}
	}

}