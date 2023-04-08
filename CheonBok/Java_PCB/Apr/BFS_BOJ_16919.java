package algorithm;
import java.io.*;
import java.util.*;
public class BOJ_16919 {
	static class node {
		int x, y;

		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int R, C, N; // R:row, C:col, N:time
	static char[][][] map;
	static int[][] delta = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[4][R][C];
		
		Queue<node> q = new LinkedList();   // 초반 폭탄이 있는 위치
		Queue<node> nq = new LinkedList();  // 초반 폭탄이 없는 위치
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				 // 초반 맵 (case1)
				 map[0][r][c] = line.charAt(c);
				if (map[0][r][c] == 'O') q.add(new node(r, c));
				else nq.add(new node(r,c));
			}
		}
	// ################################################################	
		// 폭탄 터지기 전의 모든 맵 (case3)
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[2][r], 'O');
		}
	// #################################################################
		//폭탄 터진 이후의 맵 (case2)
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[1][r], 'O');
		}
		while(!q.isEmpty()) {
			node pos = q.poll();
			map[1][pos.x][pos.y] = '.';
			
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + delta[i][0];
				int ny = pos.y + delta[i][1];
				
				if (nx<0 || nx>=R || ny<0 || ny>=C) continue;
				map[1][nx][ny] = '.';
			}
		}
	// #################################################################	
		// 다음 차례의 폭탄이 터지고 나서의 맵 (case4)
		for (int r = 0; r < R; r++) {
			Arrays.fill(map[3][r], 'O');
		}
		while(!nq.isEmpty()) {
			node pos = nq.poll();
			
			if(map[1][pos.x][pos.y] == 'O') {
				map[3][pos.x][pos.y] = '.';
				for (int i = 0; i < 4; i++) {
					int nx = pos.x + delta[i][0];
					int ny = pos.y + delta[i][1];
					
					if (nx<0 || nx>=R || ny<0 || ny>=C) continue;
					map[3][nx][ny] = '.';
				}
			}
		}
	// #################################################################
		int[] p = {0, 3, 2, 1};
		if (N == 1) printMap(0);
		else if (N%2 == 0) printMap(2);
		else printMap(p[N%4]);
			
	}
	
	private static void printMap(int n) {
		for (char[] rows : map[n]) {
			for (int c = 0; c <C ; c++) {
				System.out.print(rows[c]);
			}
			System.out.println();
		}
	}
}