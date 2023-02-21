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
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int ans = 0;
	static int[] arr;
	
	public static void func(int cnt) {
		if(cnt == N) {
			ans += 1;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(possible(cnt, i)) {
				arr[cnt] = i;
				func(cnt+1);
			}
		}
	}
	
	// arr[cnt]에 i 가능한지 확인
	public static boolean possible(int cnt, int i) {

		for(int p = 0; p < cnt; p++) {
			// 세로줄 확인
			if(arr[p] == i)
				return false;
			
			// 대각선 확인
			if(Math.abs(p-cnt) == Math.abs(arr[p] - i))
				return false;
		}
        
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		func(0);
		
		System.out.println(ans);
	}
}