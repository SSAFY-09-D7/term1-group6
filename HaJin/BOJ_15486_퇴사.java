import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N;
	public static int[][] arr;
	public static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        arr = new int[N+2][2];
        
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[N+2];
        
        dp[0] = 0;
        for(int i=1; i<=N; i++) {
        	dp[i] = Math.max(dp[i-1], dp[i]);
        	if(i+arr[i][0] <= N+1)
        		dp[i+arr[i][0]] = Math.max(dp[i]+arr[i][1], dp[i+arr[i][0]]);
        	
        }
        int max = 0;
        for(int i=1; i<=N+1; i++) {
        	if(max < dp[i])
        		max = dp[i];
        }
        System.out.println(max);
    }
}