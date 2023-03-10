import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	
	public static int N;
	public static int[] arr;			// 숫자 저장
	public static List<Integer> list;
	public static boolean[] visited;
	public static int[] ans;			// 기호 순열 저장
	public static int MAX = Integer.MIN_VALUE;
	public static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N];
		ans = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			int a = Integer.parseInt(st.nextToken());
			if(i == 0) {
				for(int j=0; j<a; j++)
					list.add(1);
			}
			else if(i == 1) {
				for(int j=0; j<a; j++)
					list.add(2);
			}
			else if(i == 2) {
				for(int j=0; j<a; j++)
					list.add(3);
			}
			else if(i == 3) {
				for(int j=0; j<a; j++)
					list.add(4);
			}
			
		}
		
		Collections.sort(list);

		func(0);

		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	public static void func(int k) {
		if(k == N-1) {
			
			int a = cal();
			
			if(a > MAX)
				MAX = a;
			if(a < MIN)
				MIN = a;
			
			return;
		}
		
		for(int i=0; i<N-1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				ans[k] = list.get(i);
				func(k+1);
				visited[i] = false;

			}
		}
	}
	
	public static int cal() {
		int a = arr[0];
		
		for(int i=0; i<N-1; i++) {
			if(ans[i] == 1) {
				a += arr[i+1];
			}
			else if(ans[i] == 2) {
				a -= arr[i+1];
			}
			else if(ans[i] == 3) {
				a *= arr[i+1];
			}
			else if(ans[i] == 4) {
				a /= arr[i+1];
			}
		}
		
		return a;
	}

}