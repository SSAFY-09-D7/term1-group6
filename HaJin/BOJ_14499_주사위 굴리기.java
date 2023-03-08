import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	
	public static int N, M, X, Y, K;
	public static int[][] arr;
	public static int[] move;
	public static int[] DICE = new int[7];	// 1~6 사용
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		move = new int[K];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<K; i++) {
			Move(move[i]);
		}
		
	}
	
	public static void Move(int num) {
		
		int x = X;
		int y = Y;
		
		// 동쪽으로 이동하는 경우
		if(num == 1) {
			y += 1;
			
			// 범위 벗어나면 return;
			if(y >= M) {
				y -= 1;
				return;
			}
			
			int tmp = DICE[2];
			DICE[2] = DICE[6];
			DICE[6] = DICE[4];
			DICE[4] = DICE[3];
			DICE[3] = tmp;
			
		}
		// 서쪽으로 이동하는 경우
		else if(num == 2) {
			y -= 1;
			
			// 범위 벗어나면 return;
			if(y < 0) {
				y += 1;
				return;
			}
			
			int tmp = DICE[2];
			DICE[2] = DICE[3];
			DICE[3] = DICE[4];
			DICE[4] = DICE[6];
			DICE[6] = tmp;
			
		}
		// 북쪽으로 이동하는 경우
		else if(num == 3) {
			x -= 1;
			
			if(x < 0) {
				x += 1;
				return;
			}
			
			int tmp = DICE[3];
			DICE[3] = DICE[5];
			DICE[5] = DICE[6];
			DICE[6] = DICE[1];
			DICE[1] = tmp;
			
		}
		// 남쪽으로 이동하는 경우
		else if(num == 4) {
			x += 1;
			
			if(x >= N) {
				x -= 1;
				return;
			}
			
			int tmp = DICE[3];
			DICE[3] = DICE[1];
			DICE[1] = DICE[6];
			DICE[6] = DICE[5];
			DICE[5] = tmp;
		}
		
		if(arr[x][y] == 0)
			arr[x][y] = DICE[6];
		else {
			DICE[6] = arr[x][y];
			arr[x][y] = 0;
		}
		
		System.out.println(DICE[3]);
		
		X = x;
		Y = y;
		
	}
}