package algorithm;

import java.io.*;
import java.util.*;


public class Solution{
	static int R, C;
	static int max;
	static int count;
	static char[][] map;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static ArrayList<Character> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
		
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R + 1][C + 1];
			visited = new boolean[R + 1][C + 1];
			
			for(int i = 1; i <= R; i++) {
				String str = br.readLine();
				for(int j = 1; j <= C; j++) {
					map[i][j] = str.charAt(j - 1);
				}
			}
			
			list = new ArrayList<>();
			max = 0;
			
			list.add(map[1][1]);
			count = 1;
			dfs(1,1);
			System.out.printf("#%d %d\n", testCase, max);
		}
	}

	private static void dfs(int row, int col) {
		
		if(max < list.size()) max = list.size();
		
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			
			if(nextRow < 1 || nextCol < 1 || nextRow > R || nextCol > C) continue;
			
			if(!seeItem(map[nextRow][nextCol]) ) {
				list.add(map[nextRow][nextCol]);
				count++;
				dfs(nextRow, nextCol);
				count--;
				list.remove(count);
			}
		}
		
	}

	private static boolean seeItem(char item) {
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) == item) return true; // 본 적이 있다.
		}
		
		return false; // 본적이 없다.
	}
}