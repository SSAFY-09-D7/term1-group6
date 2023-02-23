package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int emptyCount;
	static int[][] emptyPoints;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[9][9];
		for(int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) emptyCount++;
			}
		}
		
		emptyPoints = new int[emptyCount][2];
		int idx = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(map[i][j] == 0) {
					emptyPoints[idx][0] = i;
					emptyPoints[idx][1] = j;
					idx++;
				}
			}
		}
		
		dfs(0);
	}

	// dfs는 빈 자리만 돌게 됨
	private static void dfs(int count) {
		
		// 최종 종료 시점
		if(count == emptyPoints.length) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		int row = emptyPoints[count][0];
		int col = emptyPoints[count][1];
		
		for(int value = 1; value <= 9; value++) {
			// 넣을 수 있는 숫자라면
			if(colJudge(col, value) && rowJudge(row, value) && boxJudge(row, col, value)) {
				map[row][col] = value; // 넣는다.
				dfs(count + 1);
				map[row][col] = 0;
			}
		}
		return;
	}

	private static boolean colJudge(int col, int value) {
		for(int i = 0; i < 9; i++) {
			if(map[i][col] == value) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean rowJudge(int row, int value) {
		for(int i = 0; i < 9; i++) {
			if(map[row][i] == value) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean boxJudge(int row, int col, int value) {
		int boxStartRow = (row / 3) * 3;
		int boxStartCol = (col / 3) * 3;
		for(int i = boxStartRow, boxEndRow = boxStartRow + 3; i < boxEndRow; i++) {
			for(int j = boxStartCol, boxEndCol = boxStartCol + 3; j < boxEndCol; j++) {
				if(map[i][j] == value) {
					return false;
				}
			}
		}
		return true;
	}

}
