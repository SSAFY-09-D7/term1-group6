package algorithm;
import java.io.*;
import java.util.*;


public class Solution{
	static int N;
	static int[][] map;
	
	static int[] firstMaterial;
	static int[] secondMaterial;
	
	static int[] firstCalMaterial;
	static int[] secondCalMaterial;
	
	static int firstSum;
	static int secondSum;
	
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			firstMaterial = new int[N / 2];
			secondMaterial = new int[N / 2];
			min = Integer.MAX_VALUE;
			comb(0, 0);
			
			System.out.printf("#%d %d\n", testCase, min);
		}
	}
	private static void comb(int idx, int count) {
		if(count == N / 2) {
			secondMaterial = new int[N / 2];
			pickSecondMaterial();
			
			getSynergy(); 
			return;
		}
		
		for(int i = idx; i < N; i++) {
			firstMaterial[count] = i;
			comb(i + 1, count + 1);
		}
	}
	private static void getSynergy() {
		// 요리 원소들 조합 돌려서 합 구하기
		firstSum = 0;
		firstCalMaterial = new int[2];
		comb2(0, 0);
		
		secondSum = 0;
		secondCalMaterial = new int[2];
		comb3(0, 0);
		
		if(Math.abs(firstSum - secondSum) < min) min = Math.abs(firstSum - secondSum);
	}
	private static void comb2(int idx, int count) {
		if(count == 2) {
			firstSum += map[firstCalMaterial[0]][firstCalMaterial[1]] + map[firstCalMaterial[1]][firstCalMaterial[0]];
			return;
		}
		
		for(int i = idx; i < firstMaterial.length; i++) {
			firstCalMaterial[count] = firstMaterial[i];
			comb2(i + 1, count + 1);
		}
	}

	private static void comb3(int idx, int count) {
		if(count == 2) {
			secondSum += map[secondCalMaterial[0]][secondCalMaterial[1]] + map[secondCalMaterial[1]][secondCalMaterial[0]];
			return;
		}
		
		for(int i = idx; i < secondMaterial.length; i++) {
			secondCalMaterial[count] = secondMaterial[i];
			comb3(i + 1, count + 1);
		}
	}

	private static void pickSecondMaterial() {
		boolean[] exist = new boolean[N];
		for(int i = 0; i < firstMaterial.length; i++) {
			exist[firstMaterial[i]] = true;
		}
		
		int var = 0;
		for(int i = 0; i < secondMaterial.length; i++) {
			for(int j = var; j < exist.length; j++) {
				if(exist[j] == false) {
					secondMaterial[i] = j;
					var = j + 1;
					break;
				}
			}
		}
		
	}

}