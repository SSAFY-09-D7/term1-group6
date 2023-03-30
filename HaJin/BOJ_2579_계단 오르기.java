import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int n;
	public static int[] arr;
	public static int[] dp;	// 현재 계단 선택했을 때 최댓값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[305];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[305];
		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];
		dp[3] = Math.max(arr[1]+arr[3], arr[2]+arr[3]);
		
		for(int i=4; i<=n; i++) {
			// 바로 전 계단 선택하지 않는 경우 vs 바로 전 계단 선택하는 경우
			dp[i] = Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]);
		}
		
		System.out.println(dp[n]);
	}
}