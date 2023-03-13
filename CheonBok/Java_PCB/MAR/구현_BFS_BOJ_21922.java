import java.io.*;
import java.util.*;

// row, col, direction
class node {
	int x, y, direc;
	public node(int x, int y, int direc) {
		this.x = x;
		this.y = y;
		this.direc = direc;
	}
}

public class 구현_BFS_BOJ_21922 {
	static int[][] room;     
	static boolean[][] visited;  // 앉을 수 있는 자리
	static Queue<node> q = new LinkedList();
	static int N, M;  // 행, 열
	
	static int[][] delta = {{-1,0}, {1,0}, {0,-1}, {0,1}};  // 상,하,좌,우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		visited = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int v = Integer.parseInt(st.nextToken());
				
				// 에어컨 위치 = 큐에 담아두기 (BFS 시작용)
				if (v == 9) {
					q.add(new node(r, c, 4));
					visited[r][c] = true;
				}
				room[r][c] = v;
			}
		}
		
		// 1. q가 비었으면 에어컨 없는 것
		if (q.isEmpty()) {
			System.out.println(0);
			System.exit(0);
		}
		
		// 2. 에어컨이 1대라도 있으면 탐색 시작
		while(!q.isEmpty()) {
			node pos = q.poll();
			
			// direc = 방향 -> delta
			// 에어컨이 설치된 곳의 좌표는 direc 4를 가진다 = 4방 탐색을 하는 유일한 경우로 잡아둔다.
			if (pos.direc == 4) {    
				for (int i = 0; i < 4; i++) {
					int nx = pos.x + delta[i][0];
					int ny = pos.y + delta[i][1];
					
					// 바람이 4 방향으로 퍼질 때, 더 나아갈 수 있는지 확인
					if (0<=nx && nx<N && 0<=ny && ny<M) {
						visited[nx][ny] = true;
						checkRoom(nx, ny, i);    
						}
					}
				}
			// 에어컨 좌표에서 출발하는 바람이 아닌, 퍼지고 있는 바람이면 이곳을 들어간다.
			else {
				int nx = pos.x + delta[pos.direc][0];
				int ny = pos.y + delta[pos.direc][1];
				
				if (0<=nx && nx<N && 0<=ny && ny<M) {
					visited[nx][ny] = true;
					checkRoom(nx, ny, pos.direc);
				}
			}
		}
		
		// 바람이 지나간(true) 곳을 모두 더해서 최종 결과
		int ret = 0;
		for (boolean[] lineb : visited) {
			for (int c = 0; c < M; c++) {
				if (lineb[c]) ret++;
			}
		}
//		
//		System.out.println();
//		for (int[] lineb : room) {
//			System.out.println(Arrays.toString(lineb));
//		}
		
		System.out.println(ret);
	}

	// 시간초과 극복 : 에어컨(9) 만나면 또 같은 탐색 할거야??
	private static void checkRoom(int nx, int ny, int direc) {
		//System.out.println(nx+ " "+ ny + " "+ direc);
		
		if (room[nx][ny] == 1) {  // 벽이 될 수도 있다 1. (바람의 반사는 곧 역주행이므로 그 경우는 무시한다.)
			if (direc == 0 || direc == 1) q.add(new node(nx, ny, direc));
		}
		
		else if (room[nx][ny] == 2) {  // 벽이 될 수도 있다 2.
			if (direc == 2 || direc == 3) q.add(new node(nx, ny, direc));
		}
		
		// 굴절의 경우 어떠한 경우에도 순회를 경험할 수 없다. (혹시나 있을수도...?)
		// 따라서, 굴절은 방향을 적절히 바꿔주어 q에 넣어주면 된다.
		else if (room[nx][ny] == 3) {  
			if (direc == 0) q.add(new node(nx, ny, 3));
			else if (direc == 1) q.add(new node(nx, ny, 2));
			else if (direc == 2) q.add(new node(nx, ny, 1));
			else if (direc == 3) q.add(new node(nx, ny, 0));
		}
		
		else if (room[nx][ny] == 4) {  // 굴절 4
			if (direc == 0) q.add(new node(nx, ny, 2));
			else if (direc == 1) q.add(new node(nx, ny, 3));
			else if (direc == 2) q.add(new node(nx, ny, 0));
			else if (direc == 3) q.add(new node(nx, ny, 1));
		}
		
		// 무결점 관통. 이는 어떠한 제약이 없으므로 바람은 직선 운동한다.
		else if (room[nx][ny] == 0) q.add(new node(nx, ny, direc));
	}
}
