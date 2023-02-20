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
	static long N, r, c, ans;

	// n : 한 변 길이
	public static void func(long i, long j, long n, long cnt) {
		
		if(n == 2) {
			if(r == i && c == j) {
				ans = cnt;
			}
			else if(r == i && c == j+1){
				ans = cnt + 1;
			}
			else if(r == i+1 && c == j){
				ans = cnt + 2;
			}
			else if(r == i+1 && c == j+1){
				ans = cnt + 3;
			}
			return;
		}
		
		
		if(r < i+n/2) {
			if(c < j+n/2) {		// 1사분면
				func(i, j, n/2, cnt);
			}
			else {				// 2사분면
				func(i, j+n/2, n/2, cnt+n*n/4);
			}
		}
		else {
			if(c < j+n/2) {		// 3사분면
				func(i+n/2, j, n/2, cnt+n*n*2/4);
			}
			else {				// 4사분면
				func(i+n/2, j+n/2, n/2, cnt+n*n*3/4);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		func(0, 0, (long)Math.pow(2, N), 0);
		
		System.out.println(ans);
		
		
	}
	
}
