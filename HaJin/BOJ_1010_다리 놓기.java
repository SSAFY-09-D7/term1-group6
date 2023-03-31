import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int T, N, M;
	public static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		arr = new int[31][31];
		
		arr[0][0] = 1;
		arr[1][0] = 1;
		arr[1][1] = 1;
		
		for(int i=2; i<=30; i++) {
			for(int j=0; j<=30; j++) {
				if(j == 0 || j == i) {
					arr[i][j] = 1;
					continue;
				}
				if(j > i)
					break;
				
				arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
			}
		}
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sb.append(arr[M][N]).append("\n");
		}
		System.out.println(sb);
	}
}