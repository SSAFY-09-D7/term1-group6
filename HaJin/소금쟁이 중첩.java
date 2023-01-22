package com.ssafy.a_algorithm;

import java.util.*;

public class Algorithm {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		 // 소금쟁이 중첩 
		 
		int test_case;
		test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			// n : 배열 크기, num : 소금쟁이 수
			int n, num;
			
			n = sc.nextInt();
			num = sc.nextInt();
			boolean[][] arr = new boolean[20][20];
			int ans=0;
			
			int[][] map = new int[25][3];
			
			for(int i=1; i<=num; i++) {
				for(int j=0; j<3; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i=1; i<=num; i++) {
				
				int a,b,c;
				
				a = map[i][0];
				b = map[i][1];
				c = map[i][2];
				/*
				// 처음 위치(a, b), 방향 (1 : 하, 2: 우)
				a = sc.nextInt();
				b = sc.nextInt();
				c = sc.nextInt();
				*/
				
				if(arr[a][b]==true) {
					ans = i;
					break;
				}
				
				// 하
				if(c==1) {
					if(a+3<n) {
						if(arr[a+3][b]==true) {
							ans = i;
							break;
						}
						else
							arr[a+3][b] = true;
							
					}
					else
						continue;
					
					if(a+5<n) {
						if(arr[a+5][b]==true) {
							ans = i;
							break;
						}
						else
							arr[a+5][b] = true;
							
					}
					else
						continue;
					
					if(a+6<n) {
						if(arr[a+6][b]==true) {
							ans = i;
							break;
						}
						else
							arr[a+6][b] = true;
							
					}
					else
						continue;
					
				}
				// 우
				else if(c==2) {
					if(b+3<n) {
						if(arr[a][b+3]==true) {
							ans = i;
							break;
						}
						else
							arr[a][b+3] = true;
					}
					else continue;
					
					if(b+5<n) {
						if(arr[a][b+5]==true) {
							ans = i;
							break;
						}
						else
							arr[a][b+5] = true;
					}
					else continue;
					
					if(b+6<n) {
						if(arr[a][b+6]==true) {
							ans = i;
							break;
						}
						else
							arr[a][b+6] = true;
					}
					else continue;
					
				}
				
				
			}
			
			System.out.println("#"+t+" "+ans);
		
		}
		
		
		
	}
}






