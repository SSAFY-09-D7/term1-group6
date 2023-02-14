import java.io.*;
import java.util.*;
public class BAEK_1926 {
	
	static class point {
		int x, y;
		
		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] arr;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());  // 세로 (행 개수)
		m = Integer.parseInt(st.nextToken());  // 가로 (열 개수)
		
		arr = new int[n][m]; 		// 원본 배열
		visit = new boolean[n][m];  // 방문 배열
		
		// 원본 배열 값 입력 구간
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		
		int cnt = 0;   // 그림의 개수 = BFS가 수행된 횟수
		int size = 0;  // 그림의 크기 = BFS로 한 번에 탐색한 인접 노드의 개수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				// 배열 원소를 하나씩 탐색하면서 '1'이 나오면 해당 인덱스를 시점으로 BFS 시작
				// 추후 다른 원소의 인접 노드로 '재탐색'되는 경우를 방지하기 위해 방문 배열에 true 처리
				if (arr[i][j] == 1) {
					visit[i][j] = true;
					cnt++;
					size = Math.max(size, bfs(new point(i, j)));
				}
			}
		}
		System.out.println(cnt+"\n"+size);	
	}
	
	/*
	 *   point 객체 : '1'을 가지고 있는 행(x) 열(y) 값을 가진다.
	 *   인접한(상하좌우) 곳에 '1'을 가진 노드가 더 있다면 queue에 추가해 계속 탐색.
	 *   queue에 추가할 때마다 size(그림크기) 늘리고, 방문 배열(visit)에 방문 처리.
	 */

	private static int bfs(point p) {
		int size = 1;
		Queue<point> queue = new LinkedList<>();
		queue.offer(p);
		
		while (!queue.isEmpty()) {
			point tmp = queue.poll();
			arr[tmp.x][tmp.y] = 0;
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				// 조건 : 정해진 인덱스 범위 안에 들어있는지,  아직 한 번도 방문하지 않은 곳인지,  해당 인덱스 위치의 원소가 '1'인지.
				if (0<=nx && nx<n && 0<=ny && ny<m && !visit[nx][ny] && arr[nx][ny]==1) {
					size++;
					visit[nx][ny] = true;
					queue.offer(new point(nx, ny));
				}
			}
		}
		return size;
	}
}