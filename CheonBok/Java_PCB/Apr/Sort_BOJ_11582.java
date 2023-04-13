package algorithm;
import java.io.*;
import java.util.*;

public class BOJ_11582 {
	static int[] arr;
	static int N, rnd;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		rnd = Integer.parseInt(br.readLine());
		int term = N/rnd;
		
		int[] sortSet = new int[term];
		for (int i = 1; i <N; i += term) {
			System.arraycopy(arr, i, sortSet, 0, term);
			//System.out.println(Arrays.toString(sortSet));
			
			// 직접 MergeSort 구현한 경우는 시간초과...
			// 내장 sort의 훌륭함을 경험하자.
			Arrays.sort(sortSet);
			
			for(int j=0; j<term; j++) {
				bw.write(sortSet[j] + " ");
			}
		}
		bw.flush();
		bw.close();
	}
}
