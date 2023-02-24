package algorithm;

import java.io.*;
import java.util.*;

public class Solution{
	static int N, M;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[N + 1];
			for(int i = 1; i <= N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			visited = new boolean[N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				list[from].add(to);
				list[to].add(from);
			}
			
			int count = 0;
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					dfs(i);
					count++;	
				}
			}
			
			System.out.println("#" + testCase + " " + count);
		}
	}
	private static void dfs(int from) {
		visited[from] = true;
		
		for(int to: list[from]) {
			if(!visited[to]) dfs(to);
		}
	}
}