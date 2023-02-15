import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sel;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sel = new int[3];
		max = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0);
		System.out.println(max);
	}
	
	private static void judge() {
		int sum = 0;
		for(int i = 0; i < sel.length; i++) {
			sum += sel[i];
		}
		if(sum <= M && max < sum) {
			max = sum;
		}
		return;
	}
	
	private static void comb(int idx, int k) {
		if(k == 3) {
			judge();
			return;
		}
		
		for(int i = idx; i < arr.length; i++) {
			sel[k] = arr[i];
			comb(i + 1, k + 1);
		}
	}
}