import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int count;
	static int[] value;
	static boolean[] robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		value = new int[N * 2];
		robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N * 2; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 1;
		while(true) {
//			System.out.println("default");
//			print();
			
//			System.out.println("belt rotate");
			// 1번 벨트 회전
			beltRotate();
//			print();
			
			// 2번 이동 가능한 로봇이 있다면 이동
//			System.out.println("robot move");
			robotMove();
//			print();
			
//			System.out.println("robotAppend");
			// 3번 로봇 올릴 수 있다면 올리기
			robotAppend();
//			print();
			
			// 4번 내구도 체크
			if(!check()) break;
			// 모든 단계를 종료했다면 카운트 증가
			count++;
		}
		System.out.println(count);
	}

	private static void print() {
		System.out.println();
		System.out.println("count : " + count);
		System.out.println("-------belt value--------");
		for(int i = 0; i < N; i++) {
			System.out.print(value[i] + " ");
		}
		System.out.println();
		for(int i = N * 2 - 1; i >= N; i--) {
			System.out.print(value[i] + " ");
		}
		System.out.println();
		System.out.println("------robot value---------");
		for(int i = 0; i < N; i++) {
			System.out.print(robot[i] + " ");
		}
		System.out.println();
		System.out.println();
	}

	private static void robotAppend() {
		if(value[0] > 0) {
			robot[0] = true;
			value[0]--;
		}
	}

	private static void robotMove() {
		
		 for(int i = N - 1; i > 0; i--) {
			 robot[N - 1] = false;
			 // 이전 위치에 로봇이 있을 경우
			 if(robot[i - 1]) {
				 // 다음 위치에 로봇이 없고, 내구도가 1이상이라면
				 if(!robot[i] && value[i] >= 1) {
					 robot[i - 1] = false;
					 robot[i] = true;
					 value[i]--;
				 }
			 }
		 }
	}

	private static void beltRotate() {
		// 내구도 회전
		 int upPointValue = value[N * 2 - 1];
		 for(int i = N * 2 - 1; i > 0; i--){
			 value[i] = value[i - 1];
		 }
		 value[0] = upPointValue;
		 
		 // 로봇 회전
		 robot[N - 1] = false;
		 for(int i = N - 1; i > 0; i--) {
			 robot[i] = robot[i - 1];
		 }
		 robot[0] = false;
	}

	// 4번 ( 내구도 체크 )
	private static boolean check() {
		int cnt = 0;
		for(int i = 0; i < N * 2; i++) {
			if(value[i] == 0) cnt++;
		}
		
		if(cnt >= K) return false;
		else return true;
	}
}
