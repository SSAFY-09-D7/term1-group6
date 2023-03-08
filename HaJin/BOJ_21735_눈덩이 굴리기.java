import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	
	public static int N, M;
	public static int[] arr;
	public static int MAX = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		func(0, 1, 0);
		
		System.out.println(MAX);
		
	}
	
	public static void func(int now, int sum, int time) {
		if(time > M) {
			if(sum > MAX)
				MAX = sum;
			
			return;
		}
		
		if(now > N) {
			if(sum > MAX)
				MAX = sum;
			return;
		}
		
		int newSum = sum + arr[now];
		
		func(now+1, newSum, time+1);
		func(now+2, newSum/2, time+1);
		
	}
	
}