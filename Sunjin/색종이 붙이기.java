package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int min;
	static final int size = 10;
	static int[][] map;
	static int[] paperCount = {0, 5, 5, 5, 5, 5};
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        map = new int[size][size];
        
        for(int i = 0; i < size; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < size; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        min = Integer.MAX_VALUE;
        int cnt = 0;
        solve(0, 0, cnt);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
	}
	private static void solve(int row, int col, int cnt) {
		// basis part
		if(row > 9 && col == 0) {
			if(cnt < min) min = cnt;
			return;
		}
		
		if(col > 9) {
			solve(row + 1, 0, cnt);
			return;
		}
		else {
			// 붙여야 한다면
			if(map[row][col] == 1) {
				for(int paper = 5; paper > 0; paper--) {
					// 붙일 수 있는 종이가 존재하지 않는다면
					if(paperCount[paper] <= 0) continue;
				
					if(row + paper > size || col + paper > size) continue;
					
					boolean canPut = true;
					for(int i = row; i < row + paper; i++) {
						for(int j = col; j < col + paper; j++) {
							if(map[i][j] != 1) {
								canPut = false;
								break;
							}
						}
					}
					
					if(canPut) {
						for(int i = row; i < row + paper; i++) {
							for(int j = col; j < col + paper; j++) {
								map[i][j] = 0;
							}
						}
						paperCount[paper]--;
						solve(row, col + 1, cnt + 1);
						paperCount[paper]++;
						for(int i = row; i < row + paper; i++) {
							for(int j = col; j < col + paper; j++) {
								map[i][j] = 1;
							}
						}
					}
					
				}
			}
			// 붙일 필요가 없다면
			else {
				solve(row, col + 1, cnt);
			}
		}
	}
}
