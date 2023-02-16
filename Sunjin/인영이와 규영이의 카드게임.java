package algorithm;
import java.io.*;
import java.util.*;


public class Solution{
	static int winCount;
	static int loseCount;
	
	static int[] leftNum;
	static int[] rightNum;
	static int[] cantNum;
	
	static boolean[] visited;
	static int[] fightNum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			leftNum = new int[9];
			cantNum = new int[19];
			for(int i = 0; i < 9; i++) {
				leftNum[i] = Integer.parseInt(st.nextToken());
				cantNum[leftNum[i]] = 1;
			}
			
			rightNum = new int[9];
			
			int count = 0;
			for(int i = 1; i <= 18; i++) {
				if(cantNum[i] != 1) rightNum[count++] = i;
			}
			
			fightNum = new int[9];
			winCount = 0;
			loseCount = 0;
			visited = new boolean[9];
			
			perm(0);
			
			System.out.printf("#%d %d %d\n", testCase, loseCount, winCount);
		}
	}
	private static void perm(int idx) {
		if(idx == 9) {
			fight();
			return;
		}
		
		for(int i = 0; i < rightNum.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				fightNum[idx] = rightNum[i];
				perm(idx + 1);
				visited[i] = false;
			}
		}
		
	}
	private static void fight() {
		int leftScore = 0;
		int rightScore = 0;
		for(int i = 0; i < 9; i++) {
			if(leftNum[i] < fightNum[i]) rightScore += leftNum[i] + fightNum[i];
			else leftScore += leftNum[i] + fightNum[i];
		}
		
		if(leftScore < rightScore) winCount++;
		else loseCount++;
		
	}

}