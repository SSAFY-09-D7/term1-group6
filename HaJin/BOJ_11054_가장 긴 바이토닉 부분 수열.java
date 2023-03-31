import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static int n;
	public static List<Integer> arr;
	public static int[][] dp;	// 0행 : 정방향 증가 수 저장할 배열
								// 1행 : 역방향 증가 수 저장할 배열 ( 원 배열 뒤집어서 정방향 증가 수 저장, 다시 뒤집기)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		dp = new int[2][n];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		func(0);	// 정방향 증가 수 계산
		
		Collections.reverse(arr);
		
		func(1);	// 역방향 증가 수 계산
		
		int max = 0;
		for(int i=0; i<n; i++) {
			if(dp[0][i] + dp[1][n-i-1] > max)
				max = dp[0][i] + dp[1][n-i-1];
		}
		
		System.out.println(max-1);
	}
	
	public static void func(int idx) {
		
		// 처음 1로 초기화
		for(int i=0; i<n; i++) {
			dp[idx][i] = 1;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(arr.get(i) < arr.get(j)) {
					dp[idx][j] = Math.max(dp[idx][j], dp[idx][i] + 1);
				}
			}
		}
	}
}