import java.io.*;
import java.util.*;

public class SWEA_7793 {
	static class point {
		int x, y, level;
		
		public point(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}
	
	static Character[][] arr;
	static Queue<point> devil = new LinkedList<>();  // 악마 큐
	static Queue<point> human = new LinkedList<>();  // 사람 큐
	static int[] dx = {-1, 1, 0, 0};	// 4방 탐색 delta
	static int[] dy = {0, 0, -1, 1};
	static int path, N, M;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tcase = 1; tcase < T+1; tcase++) {
		// -------------------------------------------------------
			// 테스트 케이스마다 재사용을 위해 주요 정보들 초기화
			devil.clear(); 
			human.clear();
			path = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 행 개수
			M = Integer.parseInt(st.nextToken());  // 열 개수
			
			arr = new Character[N][M];  // 원본 배열
			int[] angel = new int[2];
		// ------------------------------------------------------
			
			for (int r = 0; r < N; r++) {
				String line = br.readLine();
				for (int c = 0; c < M; c++) {
					Character txt = line.charAt(c);
					arr[r][c] = txt;
					
					// 원본 배열에 값을 입력하는 중 주요 3개 토큰을 찾으면 각각의 정보를 queue와 배열에 담아 둔다.
					if (txt == 'S') human.offer(new point(r, c, 0));
					if (txt == '*') devil.offer(new point(r, c, 0));
					if (txt == 'D') {
						angel[0] = r;
						angel[1] = c;
					}
				}
			}
			
			// 여신님을 만나러 가는 여정 시작
			gameStart(angel[0], angel[1]);
			
			// path가 갱신되지 않았다 = 여신님을 만나지 못함
			if (path == Integer.MAX_VALUE) System.out.println("#"+tcase+" GAME OVER");
			else System.out.println("#"+tcase+" "+path);
		}
	}

	private static void gameStart(int x, int y) {
		// 사람의 이동경로가 D(여신님)를 만나기 전에 끝나버리면 GAME OVER
		// D를 만난다면 도달한 level을 사용해 path를 갱신
		// 모든 회차당 지정된 size만큼 수행해야 한다. (다른 회차의 정보도 queue에 한 번에 담기기 때문)
		while (!human.isEmpty()) {
			
			// 먼저 악마가 퍼진다.
			int size = devil.size();
			
			for (int i = 0; i < size; i++) {
				point tmp = devil.poll();
				
				for (int j = 0; j < 4; j++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					
					// 악마는 사람이 마지막에 있었던 곳, 그리고 .에 갈 수 있다.
					if(0<=nx && nx <N && 0<=ny && ny<M && 
						(arr[nx][ny] == '.' || arr[nx][ny] == 'S')) {
						arr[nx][ny] = '*';
						devil.offer(new point(nx, ny, tmp.level+1));
					}
				}
				
			}
			
			// 악마가 해당 라운드에서 모두 퍼졌다면, 사람이 움직여본다.
			size = human.size();
			for (int i = 0; i < size; i++) {
				point tmp = human.poll();
				
				// 꺼낸 좌표가 여신님 자리라면 (최솟값 갱신)
				if (tmp.x == x && tmp.y == y) {
					path = Math.min(path, tmp.level);
					return;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					
					// 사람이 갈 수 있는 경로라면 큐에 담아서 다음 회차에 진행해본다.
					if(0<=nx && nx <N && 0<=ny && ny<M && 
						(arr[nx][ny] == '.' || arr[nx][ny] == 'D')) {
						arr[nx][ny] = 'S'; // S로 만들면 다음에 중복 탐색을 방지함
						human.offer(new point(nx, ny, tmp.level+1));
					}
				}
			}
		}
	}
}

// 2차원 배열 출력 sample

//for (int i = 0; i < N; i++) {
//for (int j = 0; j < M; j++) {
//	System.out.print(arr[i][j]+" ");
//}
//System.out.println();
//}
//System.out.println();