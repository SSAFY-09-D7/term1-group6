import java.io.*;
import java.util.*;

public class Main {
	static int N, M, curRow, curCol, K;
	static int topIdx, floorIdx;
	static int[][] map;
	static int[] goDir;
	static int[] dice;
	// 동 서 북 남
	static int[] dRow = {0, 0, 0, -1, 1};
	static int[] dCol = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        curRow = Integer.parseInt(st.nextToken());
        curCol = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        goDir = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
        	goDir[i] = Integer.parseInt(st.nextToken());
        }
        
        dice = new int[7];
        floorIdx = 1;
        topIdx = 6;
        
        for(int i = 0; i < K; i++) {
        	// 다음으로 이동할 방향
        	int nextDir = goDir[i];
        	
        	// 지도에서 어느 위치로 이동할 것인지 다음 지점
        	curRow = curRow + dRow[nextDir];
        	curCol = curCol + dCol[nextDir];
        	
        	// 다음 이동할 지도 지점이 지도를 벗어난다면 해당 명령 무시
        	if(curRow < 0 || curCol < 0 || curRow >= N || curCol >= M) {
        		curRow -= dRow[nextDir];
        		curCol -= dCol[nextDir];
        		continue;
        	}
        	
        	// 주사위 굴려보자
        	roleDice(nextDir);
        	
        	// 이동한 다음 칸 바닥에 0이 쓰여있을 경우!
        	if(map[curRow][curCol] == 0) {
        		map[curRow][curCol] = dice[1];
        	}
        	else {
        		dice[1] = map[curRow][curCol];
        		map[curRow][curCol] = 0;
        	}
        	
        	System.out.println(dice[6]);
        }
        
	}
	private static void roleDice(int dir) {
		// 우로 주사위 굴려보자
		if(dir == 1) {
			int tmp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
		}
		
		// 좌로 주사위 굴려보자
		if(dir == 2) {
			int tmp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
		}
		
		// 뒤로 주사위 굴려보자
		if(dir == 3) {
			int tmp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
		}
		
		// 앞으로 주사위 굴려보자
		if(dir == 4) {
			int tmp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
		}
	}
}