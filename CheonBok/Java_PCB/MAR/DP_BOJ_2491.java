package Algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2491 {
	static int[] upper;
	static int[] lower;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		upper = new int[N];
		lower = new int[N];
		int maxl = 0;
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		upper[0] = 1; lower[0] = 1;
		
		for (int i = 1; i < N; i++) {
			// 연속 증가수열
			if (arr[i-1] <= arr[i]) upper[i] = upper[i-1]+1;
			else if (arr[i-1] > arr[i]) upper[i] = 1;
			
			maxl = Math.max(upper[i], maxl);
			
			// 연속 감소수열
			if (arr[i-1] >= arr[i]) lower[i] = lower[i-1]+1;
			else if (arr[i-1] < arr[i]) lower[i] = 1;
			
			maxl = Math.max(lower[i], maxl);
		}
		
		//System.out.println(Arrays.toString(upper));
		//System.out.println(Arrays.toString(lower));
		if (maxl == 0) System.out.println(1);
		else System.out.println(maxl);
	}
}
