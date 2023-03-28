import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n;
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		
		func(n);
		
		System.out.println(arr[n]);
	}
	
	public static int func(int n) {
		
		if(n == 1)
			return 0;
		
		if(arr[n] != 0)
			return arr[n];
		
		int tmp1 = Integer.MAX_VALUE;
		int tmp2 = Integer.MAX_VALUE;
		int tmp3 = Integer.MAX_VALUE;
		
		if(n%2 == 0) 
			tmp1 = func(n/2) + 1;
		
		if(n%3 == 0)
			tmp2 = func(n/3) + 1;
		
		tmp3 = func(n-1) + 1;
		
		return arr[n] = Math.min(Math.min(tmp1, tmp2), tmp3);
	}
}
