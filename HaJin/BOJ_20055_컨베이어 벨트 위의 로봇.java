import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	
	public static int N, K;
	public static int[][] arr;	// 내구도, 현재 로봇 여부 저장(0: 로봇x, 1: 로봇 o)
	public static int[][] newArr;
	public static int CNT = 0;
	public static int ANS = 1;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N*2 + 1][2];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N*2; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			newArr = new int[N*2 + 1][2];
			
			// 1. 벨트 한 칸 회전
			for(int i=2*N-1; i>=1; i--) {
				newArr[i+1][0] = arr[i][0];
				newArr[i+1][1] = arr[i][1];
			}
			newArr[1][0] = arr[2*N][0];
			newArr[1][1] = 0;

			// 2. 로봇 이동
			newArr[N][1] = 0;
			for(int i=N-1; i>=1; i--) {
				if(newArr[i+1][1] == 0 && newArr[i+1][0] >= 1 && newArr[i][1] == 1) {
					newArr[i+1][1] = 1;
					newArr[i][1] = 0;
					newArr[i+1][0] -= 1;
				}
			}

			// 3. 로못 올리기
			if(newArr[1][0] >= 1) {
				newArr[1][1] = 1;
				newArr[1][0] -= 1;
				
			}
			
            // 4.
			int cnt = 0;
			for(int i=1; i<=N*2; i++) {
				if(newArr[i][0] == 0)
					cnt += 1;
			}
			
			if(cnt >= K)
				break;
			
			ANS += 1;
			
			for(int i=1; i<=N*2; i++) {
				arr[i][0] = newArr[i][0];
				arr[i][1] = newArr[i][1];
			}
		}
		
		System.out.println(ANS);
	}
}