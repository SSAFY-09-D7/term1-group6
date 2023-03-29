import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int T, n;
	public static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		dp = new long[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<=1000000; i++) {
			dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009;
		}
		
		for(int t=0; t<T; t++) {
			n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}
}