import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	StringBuilder sb = new StringBuilder();
	
	static int[][] arr;
	//static int[][] newArr;
	static int[] ans;	// 0 ~ k-1 순열 저장할 배열
	static boolean[] visited;
	static int[][] rotation;
	static int N, M, K;
	static int min_ans = Integer.MAX_VALUE;
	
	static int[] rotateX = {1, 0, -1, 0};
	static int[] rotateY = {0, 1, 0, -1};

	
	public static void func(int k) {
		if(k == K) {
			// new Arr 생성 후 newArr 배열돌리기
			// rotate 함수 넣을 때 r, c 1씩 빼고 넣기
			int[][] newArr = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++)
					newArr[i][j] = arr[i][j];
			}
			
			// ans 배열에 있는 순서대로 회전
			for(int i=0; i<K; i++) {
				newArr = rotate(newArr, rotation[ans[i]][0] - 1, rotation[ans[i]][1] - 1, rotation[ans[i]][2]);
			}
			
			int sum = Integer.MAX_VALUE;
			
			// 각 행의 합 중 최솟값 구하기
			for(int i=0; i<N; i++) {
				int tmpSum = 0;
				for(int j=0; j<M; j++) {
					tmpSum += newArr[i][j];
				}
				
				if(sum > tmpSum)
					sum = tmpSum;
			}
			
			if(min_ans > sum)
				min_ans = sum;
			
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				ans[k] = i;
				func(k+1);
				visited[i] = false;
			}
		}

	}
	
	// 2 3 2
	public static int[][] rotate(int[][] newArr, int r, int c, int s) {
		
		for(int i=0; i<s; i++) {
			int x = r - s + i;	// 시작점 col
			int y = c - s + i;	// 시작점 row
			
			int tmp = newArr[x][y];
			
			int idx = 0;
			
			while(idx<4) {
				int xx = x + rotateX[idx];
				int yy = y + rotateY[idx];
				
				// 범위 안에 들어오면
				if(xx <= r+s-i && xx >= r-s+i && yy <= c+s-i && yy >= c-s+i) {
					newArr[x][y] = newArr[xx][yy];
					x = xx;
					y = yy;
				}
				else
					idx += 1;
			}
			newArr[r-s+i][c-s+i+1] = tmp;	
		}
        
		return newArr;
	}

	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		rotation = new int[K][3];
		ans = new int[K];
		visited = new boolean[K];
		//newArr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			rotation[i][0] = Integer.parseInt(st.nextToken());	// r
			rotation[i][1] = Integer.parseInt(st.nextToken());	// c
			rotation[i][2] = Integer.parseInt(st.nextToken());	// s
		}
		
		func(0);

		System.out.println(min_ans);
	}
}