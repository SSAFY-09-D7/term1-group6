import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	
	public static int N, M, K;
	public static int[][] arr;
	public static int X = 0;
	public static int Y = 0;
	public static int Dir = 0;	// 0: 동, 1: 서, 2: 남, 3: 북
	public static int ANS = 0;
	public static boolean[][] visited;
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	public static int[] DICE;
	
	public static class Node{
		int i;
		int j;
		
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DICE = new int[7];
		for(int i=1; i<=6; i++) {
			DICE[i] = i;
		}
		
		int cnt = 0;
		
		while(true) {
			if(cnt == K) {
				break;
			}
			
			// 1. 주사위 한 칸 이동 (이동 방향에 칸 없으면 반대방향)
			move();
			
			// 2. 도착한 칸에 대한 점수 획득
			getScore();
			
			// 3. 주사위 아랫면과 해당 칸 정수 비교해 이동방향 결정
			makeDir();
			
			cnt += 1;
		}

		System.out.println(ANS);
	
	}
	
	public static void move() {
		int x = X;
		int y = Y;
		
		if(Dir == 0) {
			y += 1;
			
			if(y >= M) {
				y -= 2;
				Dir = 1;
			}
				
		}
		else if(Dir == 1) {
			y -= 1;
			
			if(y < 0) {
				y += 2;
				Dir = 0;
			}
				
		}
		else if(Dir == 2) {
			x += 1;
			
			if(x >= N) {
				x -= 2;
				Dir = 3;
			}
				
		}
		else if(Dir == 3) {
			x -= 1;
			
			if(x < 0) {
				x += 2;
				Dir = 2;
			}
		}
		
		X = x;
		Y = y;
		
		changeDice();
	}
	
	public static void changeDice() {
		
		if(Dir == 0) {			// 동
			int tmp = DICE[6];
			DICE[6] = DICE[3];
			DICE[3] = DICE[1];
			DICE[1] = DICE[4];
			DICE[4] = tmp;
		}
		else if(Dir == 1) {		// 서
			int tmp = DICE[6];
			DICE[6] = DICE[4];
			DICE[4] = DICE[1];
			DICE[1] = DICE[3];
			DICE[3] = tmp;
		}
		else if(Dir == 2) {		// 남
			int tmp = DICE[6];
			DICE[6] = DICE[5];
			DICE[5] = DICE[1];
			DICE[1] = DICE[2];
			DICE[2] = tmp;
		}
		else if(Dir == 3) {		// 북
			int tmp = DICE[6];
			DICE[6] = DICE[2];
			DICE[2] = DICE[1];
			DICE[1] = DICE[5];
			DICE[5] = tmp;
		}
		
	}
	
	public static void getScore() {
		
		int B = arr[X][Y];
		
		// (X, Y)에서 동서남북 방향으로 연속해서 이동할 수 있는 칸의 수 구하기
		int C = bfs(B);
		
		ANS += B * C;
	}
	
	public static int bfs(int B) {
		
		int cnt = 1;
		visited = new boolean[N][M];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(X, Y));
		visited[X][Y] = true;
		
		while(!queue.isEmpty()) {
			
			Node node = queue.poll();
			
			int i = node.i;
			int j = node.j;
			
			for(int p=0; p<4; p++) {
				
				int ii = i + nr[p];
				int jj = j + nc[p];
				
				if(ii>=0 && ii<N && jj>=0 && jj<M && !visited[ii][jj] && arr[ii][jj]==B) {
					cnt += 1;
					queue.add(new Node(ii, jj));
					visited[ii][jj] = true;
				}
			}
			
		}
		
		return cnt;
	}
	
	public static void makeDir() {
		int A = DICE[6];
		int B = arr[X][Y];
		
		if(A > B) {
			// 90도 시계방향
			if(Dir == 0)
				Dir = 2;
			else if(Dir == 1)
				Dir = 3;
			else if(Dir == 2)
				Dir = 1;
			else if(Dir == 3)
				Dir = 0;
		}
		else if(A < B) {
			// 90도 반시계방향
			if(Dir == 0)
				Dir = 3;
			else if(Dir == 1)
				Dir = 2;
			else if(Dir == 2)
				Dir = 0;
			else if(Dir == 3)
				Dir = 1;
		}
		
	}
    
}