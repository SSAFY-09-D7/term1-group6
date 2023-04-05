package algorithm;

import java.io.*;
import java.util.*;

public class SWEA_1486 {
	static int N, B, ret; 
	static int[] member;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점원 수
			B = Integer.parseInt(st.nextToken()); // 선반 높이
			member = new int[N];  // 각 점원의 키
			ret = 200000;  //가능한 범위에서의 최솟값을 구하기 위해 최대로 설정 (최대 점원 20, 점원 당 최대 키 10000)
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				member[c] = Integer.parseInt(st.nextToken());
			}
			
			//idx:선택된 점원, total: 포함된 점원들의 키의 합
			dfs(0, 0);
			sb.append("#"+t+" "+ret+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int idx, int total) {
		// 점원들 키의 합이 요구사항을 충족할 때, 최솟값으로 반영
		if (total >= B) {
			ret = Math.min(ret, total-B);
			return;
		}
		
		// 모든 점원을 탐색했을 때, 결과가 요구사항을 충족하면 최솟값 반영
		if(idx == N) {
			if(total>=B) ret = Math.min(ret, total-B);
			return;
		}
		
		//점원을 포함시키거나 포함시키지 않을 경우에 대한 dfs 처리
		dfs(idx+1, total+member[idx]);
		dfs(idx+1, total);
	}
}
