import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int max;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		solve(0, 1, 0);
		System.out.println(max);
	}
	private static void solve(int point, int size, int time) {
		if(time > M) {
			if(max < size) max = size;
			return;
		}
		
		if(point > arr.length - 1) {
			if(max < size) max = size;
			return;
		}
		
		size += arr[point];
		
		solve(point + 1, size, time + 1);
		
		solve(point + 2, size / 2, time + 1);
	}
}
