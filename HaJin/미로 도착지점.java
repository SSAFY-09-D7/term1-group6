package com.ssafy.a_algorithm;

import java.util.*;

public class Algorithm {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		int test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			
			int[][] arr = new int[25][25];
			int[][] move_arr = new int[25][2];
			
			int N = sc.nextInt();
			int start_x = sc.nextInt() + 1;
			int start_y = sc.nextInt() + 1;
			int jumper_n = sc.nextInt();
			
			for(int i=0; i<jumper_n; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				arr[a+1][b+1] = 1;
			}
			
			int move_n = sc.nextInt();
			
			for(int i=0; i<move_n; i++) {
				move_arr[i][0] = sc.nextInt(); // 방향 (1:상,  2:우,  3:하,  4:좌)
				move_arr[i][1] = sc.nextInt(); // 이동 칸 수
			}
			
			for(int i=0; i<move_n; i++) {
				if(move_arr[i][0] == 1) {
					start_x -= move_arr[i][1];
				}
				else if(move_arr[i][0] == 2) {
					start_y += move_arr[i][1];
				}
				else if(move_arr[i][0] == 3) {
					start_x += move_arr[i][1];
				}
				else if(move_arr[i][0] == 4) {
					start_y -= move_arr[i][1];
				}
				
				if(start_x<=0 || start_x>N || start_y<=0 || start_x>N || arr[start_x][start_y]==1) {
					start_x = 0;
					start_y = 0;
					break;
				}
			}
			
			if(start_x != 0 && start_y !=0) {
				start_x -= 1;
				start_y -= 1;
			}
		
			System.out.printf("#%d %d %d\n", t, start_x, start_y);
		
		}
	}
}






