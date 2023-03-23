import java.io.*;
import java.util.*;

public class BOJ_14716 {
	static class node {
		int x, y;
		public node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static Character[][] arr;
	static int[][] delta = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Character[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = st.nextToken().charAt(0);
			}
		}

		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (arr[r][c] == '1') {
					check(r, c);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}

	// 8방에서 인접한 곳에 1이 있으면 0으로 바꾸고 방문
	private static void check(int x, int y) {
		for(int[] d : delta){
			int nx = x + d[0];
			int ny = y + d[1];
				
			if (0<=nx && nx<N && 0<=ny && ny<M && arr[nx][ny] == '1') {
					arr[nx][ny] = '0';
					check(nx, ny);
				}
			}
		}
	}

