// BAEK 1743 음식물 피하기
import java.io.*;
import java.util.*;

public class BAEK_1743 {
	static class point {
		int x, y;
		
		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] arr;
	static int rnd;
	static int[] dx = {-1, 1, 0, 0};	// 4방 탐색 delta
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visit;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr[x][y] = 1;
		}
		
	
		int size = 0;
		rnd = 0;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				if (arr[i][j] == 1) {
					visit[i][j] = true;
					checkSize(new point(i, j));
					size = Math.max(rnd, size);
					rnd = 0;	
				}
			}
		}
		System.out.println(size);
	}

	private static void checkSize(point p) {
		Queue<point> queue = new LinkedList<>();	
		queue.offer(p);
		
		while (!queue.isEmpty()) {
			point tmp = queue.poll();
			rnd++;
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				if (1 <= nx && nx < N+1 && 1 <= ny && ny < M+1 && !visit[nx][ny] && arr[nx][ny] == 1) {
					queue.offer(new point(nx, ny));
					visit[nx][ny] = true;
				}
			}
		}
	}
}