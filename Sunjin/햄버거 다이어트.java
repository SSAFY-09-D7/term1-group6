import java.util.Scanner;

public class Solution {
	static int N;
	static int L;
	static int[] taste;
	static int[] kal;
	static boolean[] visited;
	static int count = 0;
	static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
				
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			L = sc.nextInt();
			
			taste = new int[N];
			kal = new int[N];
			visited = new boolean[N];
			
			for(int person = 0; person < N; person++) {
				taste[person] = sc.nextInt();
				kal[person] = sc.nextInt();
			}
			
			result = 0;
			dfs(0, 0, 0);
			System.out.println("#" + test_case + " " + result);
		}
	}
	public static void dfs(int count, int score, int kcal) {
		if(kcal > L) {
			return;
		}
		if(count == N) {
			result = Math.max(result, score);
			return;
		}
		dfs(count + 1, score + taste[count], kcal + kal[count]);
		dfs(count + 1, score, kcal);
	}
}
