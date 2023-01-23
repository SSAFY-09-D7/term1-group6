package com.ssafy.a_algorithm;

import java.util.*;

public class Algorithm {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int test_case;
		test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			
			boolean[][] arr = new boolean[20][20];
			int[][] map = new int[25][3];
			int size = sc.nextInt();	// 연못 크기
			int n = sc.nextInt();		// 소금쟁이 수
			int die = 0;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < 3; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i < n; i++) {
				
				int a,b,c;
				
				a = map[i][0]; // 시작위치
				b = map[i][1]; // 시작위치
				c = map[i][2]; // 1-상  2-하  3-좌  4-우
				
				if(c==1) {
					if((a-6) < 0 || arr[a-6][b] || arr[a][b] || arr[a-3][b] || arr[a-5][b]) {
						die++;
					}
					else {
						arr[a-6][b] = true;
					}
				}
				else if(c==2) {
					if((a+6) >= size || arr[a+6][b] || arr[a][b] || arr[a+3][b] || arr[a+3][b]) {
						die++;
					}
					else {
						arr[a+6][b] = true;
					}
					
				}
				else if(c==3) {
					if((b-6) < 0 || arr[a][b-6] || arr[a][b] || arr[a][b-3] || arr[a][b-5]) {
						die++;
					}
					else {
						arr[a][b-6] = true;
					}
				}
				else if(c==4) {
					if((b+6) >= size || arr[a][b+6] || arr[a][b] || arr[a][b+3] || arr[a][b+5]) {
						die++;
					}
					else {
						arr[a][b+6] = true;
					}
				}
				
				
			}
		
			System.out.println("#"+t+" "+(n-die));
			
		}
		
		
		
	}
}






