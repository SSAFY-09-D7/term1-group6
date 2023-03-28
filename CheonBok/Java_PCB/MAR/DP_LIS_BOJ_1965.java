package Algorithm;
import java.io.*;
import java.util.*;
public class BOJ_1965 {
	static int[] arr, dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		int maxl = 0;
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(dp, 1);  // 최장 부분 수열 값 담는 배열
		
		
		// LIS : 최장 증가 부분 수열
		for (int target = 1; target<N; target++){
			int tmp = 0; // 각 인덱스에서 가져올 수 있는 값(연결된 개수)
		    
			/*
			 *   target은 현재 보고 있는 상자의 index
			 *   i는 sub이며 각 인덱스 위치에는 이전(i)까지 담을 수 있었던 최대 개수가 저장되어 있다.
			 *   전부 탐색해서 가져올 수 있는 상자 중 가장 많은 개수를 tmp에 담는 과정.
			 * 
			 */
			for (int i = target-1; i >= 0; i--){
		        if(arr[i] < arr[target]){
		            tmp = Math.max(dp[i], tmp);
		        }
		    }
			dp[target] += tmp; // 가져올 수 있는 가장 많은 상자의 개수를 더해서 Update
		}
		
		for (int i = 0; i < N; i++) {
			if (maxl < dp[i]) maxl = dp[i];
		}
		System.out.println(maxl);
	}
}