package algorithm;
import java.io.*;
import java.util.*;
public class BOJ_3055_탈출 {
	static class node {
		int r, c, level;
		public node(int r, int c, int level) {
			this.r = r;
			this.c = c;
			this.level = level;
		}
	}
	
	static int R, C;
	static char[][] arr;
	static int[][] wpool;
	static int[][] delta = {{-1,0}, {1,0}, {0,1}, {0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[R][C];
		wpool = new int[R][C];
		
		for (int[] rows : wpool) {
			Arrays.fill(rows, 251);
		}
		
		Queue<node> waterq = new LinkedList<>();
		int[] ani = {0, 0};
		int[] home = {0, 0};
		int ret = -1;
		
		//방문배열 적용 (물)
		boolean[][] wv = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				char v = line.charAt(c);
				arr[r][c] = v;
							
				// 물은 여러 곳 있을 수 있으므로 Queue에 즉시 담아둔다.
				if (v == '*') {  
					waterq.add(new node(r, c, 0));
					wpool[r][c] = 0;
					wv[r][c] = true;
				}
				
				// 고슴도치는 위치 찾았으면 물이 이동 가능하게 '.'으로 변경
				if (v == 'S') {
					ani[0] = r;
					ani[1] = c;
					arr[r][c] = '.';
				}
				
				// 도착지
				if (v == 'D') {
					home[0] = r;
					home[1] = c;
				}
			}
		}
	// #########################################################################
		/*
		 *  X: 돌
		 *  .: 빈 곳
		 *  *: 물
		 *  D: 비버굴
		 *  S: 고슴도치 위치
		 *  
		 */
		// #1. 물이 퍼지는 시간을 미리 계산한다. (BFS)
		//     wpool: 물이 퍼지는 시간을 저장하는 Array
		while (!waterq.isEmpty()) {
			node pos = waterq.poll();
			for (int d = 0; d < 4; d++) {
				int nx = pos.r + delta[d][0];
				int ny = pos.c + delta[d][1];
				if (nx<0 || nx>=R || ny<0 || ny>=C) continue;
				
				if(arr[nx][ny] == '.' && !wv[nx][ny]) {
					wv[nx][ny] = true;
					wpool[nx][ny] = pos.level+1;
					waterq.add(new node(nx, ny, pos.level+1));
				}
			}
		}
//		
//		for (int[] rows :wpool) {
//			System.out.println(Arrays.toString(rows));
//		}
//		System.out.println();
		
		
		// #2. 고슴도치가 도착지까지 움직인다.
		//     단, 이동하려는 위치에 도달하는 시간이 물이 도착하려는 시간보다 이전이어야 한다.
		Queue<node> moveq = new LinkedList<>();
		boolean[][] mv = new boolean[R][C];
		moveq.add(new node(ani[0], ani[1], 0));
		mv[ani[0]][ani[1]] = true;
		
		while(!moveq.isEmpty()) {
			node pos = moveq.poll();
			
			if (pos.r == home[0] && pos.c == home[1]) {
				ret = pos.level;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = pos.r + delta[d][0];
				int ny = pos.c + delta[d][1];
				if (nx<0 || nx>=R || ny<0 || ny>=C) continue;
				
				if ((arr[nx][ny] == '.' || arr[nx][ny]=='D') && !mv[nx][ny] && wpool[nx][ny]> pos.level+1) {
					mv[nx][ny] = true;
					moveq.add(new node(nx, ny, pos.level+1));
				}
			}
		}
		
//		for (boolean[] rows : mv) {
//			System.out.println(Arrays.toString(rows));
//		}
//		System.out.println();
		
		if (ret == -1) System.out.println("KAKTUS");
		else System.out.println(ret);
	}
}