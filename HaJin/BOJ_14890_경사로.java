import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		int[][] arr = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 가로줄 탐색
		for(int i=0; i<N; i++) {
			
			int start = arr[i][0];
			int cnt = 1;
			boolean available = true;
			boolean[][] exists = new boolean[N][N];
			
			// 증가하는 경우
			for(int j=1; j<N; j++) {
				
				if(start == arr[i][j])
					cnt += 1;
				else if(cnt >= L && arr[i][j] == start+1){
					start = arr[i][j];
					cnt = 1;
					
					// exists 배열에 경사로 표시
					for(int b=1; b<=L; b++) {
						exists[i][j-b] = true;
					}
					cnt = 1;
				}
				else
					cnt = 1;
				start = arr[i][j];
			}
			
			// 감소하는 경우
			start = arr[i][N-1];
			cnt = 1;
			
			for(int j=N-2; j>=0; j--) {
				
				if(start == arr[i][j])
					cnt += 1;
				else if(cnt >= L && arr[i][j] == start+1){
					start = arr[i][j];
					cnt = 1;
					
					// exists 배열에 경사로 표시
					for(int b=1; b<=L; b++) {
						if(exists[i][j+b])
							available = false;
						exists[i][j+b] = true;
					}
					cnt = 1;
				}
				else
					cnt = 1;
				start = arr[i][j];
			}
			
			int s = arr[i][0];
			for(int j=1; j<N; j++) {
				
				if(arr[i][j] > arr[i][j-1]) {	// 증가하는 경우
					if(!exists[i][j-1]) {
						available = false;
						break;
					}
				}
				else if(arr[i][j] < arr[i][j-1]) {	// 감소하는 경우
					if(!exists[i][j]) {
						available = false;
						break;
					}
				}
				
				s = arr[i][j];
			}
			if(available) {
				ans += 1;
				//System.out.println("i : "+ i);
			}
		}
		


		
		
		// 세로줄 탐색
		for(int i=0; i<N; i++) {
			
			int start = arr[0][i];
			int cnt = 1;
			boolean available = true;
			boolean[][] exists = new boolean[N][N];
			
			// 증가하는 경우
			for(int j=1; j<N; j++) {
				
				if(start == arr[j][i])
					cnt += 1;
				else if(cnt >= L && arr[j][i] == start+1){
					start = arr[j][i];
					cnt = 1;
					
					// exists 배열에 경사로 표시
					for(int b=1; b<=L; b++) {
						exists[j-b][i] = true;
					}
					cnt = 1;
				}
				else
					cnt = 1;
				start = arr[j][i];
			}
			
			// 감소하는 경우
			start = arr[N-1][i];
			cnt = 1;
			
			for(int j=N-2; j>=0; j--) {
				
				if(start == arr[j][i])
					cnt += 1;
				else if(cnt >= L && arr[j][i] == start+1){
					start = arr[j][i];
					cnt = 1;
					
					// exists 배열에 경사로 표시
					for(int b=1; b<=L; b++) {
						if(exists[j+b][i])
							available = false;
						exists[j+b][i] = true;
					}
					cnt = 1;
				}
				else
					cnt = 1;
				start = arr[j][i];
			}
			
			int s = arr[0][i];
			for(int j=1; j<N; j++) {
				
				if(arr[j][i] > arr[j-1][i]) {	// 증가하는 경우
					if(!exists[j-1][i]) {
						available = false;
						break;
					}
				}
				else if(arr[j][i] < arr[j-1][i]) {	// 감소하는 경우
					if(!exists[j][i]) {
						available = false;
						break;
					}
				}
				
				
				s = arr[j][i];
			}
            
			if(available) {
				ans += 1;
				//System.out.println("j : "+ i);
			}
		}
		System.out.println(ans);
	}
}
