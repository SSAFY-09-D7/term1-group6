// SWEA 1953 탈주범 검거
import java.io.*;
import java.util.*;

// Queue에서 활용하기 위해 현재 위치한 파이프의 좌표(r,c)와
// 해당 파이프의 위치까지 가는데 걸린 시간(times)을 저장하는 class
class pipe {
	int r, c, times;
	
	public pipe (int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.times = time;
	}
}

public class SWEA_1953 {
	
	static int N, M, R, C, L;
	static int[][] pipeLine;   // 탈출 맵
	static boolean[][] visit;  // 이동할 수 있는 위치 (방문 처리)
	static Queue<pipe> load = new LinkedList<>(); // BFS 목적, 이동 가능한 파이프 객체 정보를 담는다.
	static int[] dx = {-1, 1, 0, 0}; // 상하좌우
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로 크기 (행)
			M = Integer.parseInt(st.nextToken()); // 가로 크기 (열)
			R = Integer.parseInt(st.nextToken()); // 시작 행 좌표
			C = Integer.parseInt(st.nextToken()); // 시작 열 좌표
			L = Integer.parseInt(st.nextToken()); // 탈출 시간
			
			pipeLine = new int[N][M];  // 파이프 지도
			visit = new boolean[N][M]; // 이동할 수 있는 위치 (방문 처리)
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					pipeLine[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// row, col, times
			load.offer(new pipe(R, C, 1));  // 첫 시작 파이프 객체를 Queue에 담기
			runAway();  // 탈출 여졍 시작
			
			
		// ----------------------------------------------------------
			// 방문 처리 맵에서 true인 곳은 이동 가능한 파이프 위치이므로 개수에 추가.
			int ret = 0;
			for (boolean[] bl : visit) {
				for (int i = 0; i < M; i++) {
					if (bl[i]) ret++;
				}
			}
		// ----------------------------------------------------------
			sb.append("#"+t+" "+ret+"\n");
		}
		System.out.println(sb);
	}

	
	/*
	 *   파이프의 종류는 총 7가지.
	 *   파이프의 종류와 이동하려는 방향에 따라서 이동할 수 있는지의 유무가 결정된다.
	 *   따라서 파이프의 종류(1~7)와 이동 방향(delta)를 바탕으로 각각의 경우에 대해 이동 가능한 파이프라면
	 *   이동 가능한 시간 안에서 최대한 이동한다.
	 *   
	 *   -delta-
	 *   0:상, 1:하, 2:좌, 3:우
	 */
	private static void runAway() {
		
		while (!load.isEmpty()) {
			pipe loc = load.poll();
			visit[loc.r][loc.c] = true; // 현재 위치한 파이프는 방문 처리
			
			// 다음 이동을 할 수 없는 시간이면 다음 처리
			if (loc.times == L) continue;
			
			
			// 1번 파이프 처리 (delta = 0, 1, 2, 3)
			if (pipeLine[loc.r][loc.c] == 1) {
				for (int i = 0; i < 4; i++) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType1(i, nx, ny, loc.times+1);
					}
				}
			}
			
			// 2번 파이프 처리 (delta = 0, 1)
			if (pipeLine[loc.r][loc.c] == 2) {
				for (int i = 0; i < 2; i++) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType2(i, nx, ny, loc.times+1);
					}
				}
			}
			
			// 3번 파이프 처리 (delta = 2, 3)
			if (pipeLine[loc.r][loc.c] == 3) {
				for (int i = 2; i < 4; i++) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType3(i, nx, ny, loc.times+1);
					}
				}
			}
			
			// 4번 파이프 처리 (delta = 0, 3)
			if (pipeLine[loc.r][loc.c] == 4) {
				for (int i = 0; i < 4; i+=3) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType4(i, nx, ny, loc.times+1);
					}
				}
			}
			
			// 5번 파이프 처리 (delta = 1, 3)
			if (pipeLine[loc.r][loc.c] == 5) {
				for (int i = 1; i < 4; i+=2) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType5(i, nx, ny, loc.times+1);
					}
				}
			}
			
			// 6번 파이프 처리 (delta = 1, 2)
			if (pipeLine[loc.r][loc.c] == 6) {
				for (int i = 1; i < 3; i++) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType6(i, nx, ny, loc.times+1);
					}
				}
			}
			
			// 7번 파이프 처리 (delta = 0, 2)
			if (pipeLine[loc.r][loc.c] == 7) {
				for (int i = 0; i < 3; i+=2) {
					int nx = loc.r + dx[i];
					int ny = loc.c + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && 
							!visit[nx][ny] && pipeLine[nx][ny] != 0) {
						checkType7(i, nx, ny, loc.times+1);
					}
				}
			}
		}
	}
	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType7(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		
		// 좌 검사
		if (direc == 2 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 5)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 상 검사
		if (direc == 0 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 5 || pipeLine[nx][ny] == 6)) {
			load.offer(new pipe(nx, ny, time));
		}
	}
	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType6(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		// 하 검사
		if (direc == 1 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 좌 검사
		if (direc == 2 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 5)) {
			load.offer(new pipe(nx, ny, time));
		}
	}
	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType5(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		// 우 검사
		if (direc == 3 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 6 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 하 검사
		if (direc == 1 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
	}
	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType4(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		// 상 검사
		if (direc == 0 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 5 || pipeLine[nx][ny] == 6)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 우 검사
		if (direc == 3 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 6 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
		
	}

	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType3(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		// 좌 검사
		if (direc == 2 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 5)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 우 검사
		if (direc == 3 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 6 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}		
	}

	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType2(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		// 상 검사
		if (direc == 0 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 5 || pipeLine[nx][ny] == 6)) {
			load.offer(new pipe(nx, ny, time));
		}
		
		// 하 검사
		if (direc == 1 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
	}

	
	
	
	// 0:상, 1:하, 2:좌, 3:우
	private static void checkType1(int direc, int nx, int ny, int time) {
		if (pipeLine[nx][ny] == 1) load.offer(new pipe(nx, ny, time));
		// 상 검사
		if (direc == 0 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 5 || pipeLine[nx][ny] == 6)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 하 검사
		if (direc == 1 && (pipeLine[nx][ny] == 2 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 좌 검사
		if (direc == 2 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 4 || pipeLine[nx][ny] == 5)) {
			load.offer(new pipe(nx, ny, time));
		}
		// 우 검사
		if (direc == 3 && (pipeLine[nx][ny] == 3 || pipeLine[nx][ny] == 6 || pipeLine[nx][ny] == 7)) {
			load.offer(new pipe(nx, ny, time));
		}
	}
}
