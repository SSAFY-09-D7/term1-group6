package com.ssafy.a_algorithm;

import java.util.*;

public class Algorithm {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int test_case;
		test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			
			int X = sc.nextInt();	// 배열크기 i
			int Y = sc.nextInt();	// 배열크기 j
			int N = sc.nextInt();   // 참가자 수
			
			int[][] direction = new int[20][20];	// 방향
			int[][] jump = new int[20][20];			// 점프 칸 수
			int[][] score = new int[20][20];		// 점수
			int[][] human = new int[5][3];			// 참가자 정보 (행, 열, 점프 횟수)
			
			int money = 0;
			
			
			for(int i=1; i<=X; i++) {
				for(int j=1; j<=Y; j++) {
					int tmp = sc.nextInt();
					direction[i][j] = tmp/10;		// 1-오른  2-아래  3-왼쪽  4-위쪽
					jump[i][j] = tmp%10;
					score[i][j] = tmp;
				}
			}
			
			
			// 참가자 정보 (행, 열, 점프 횟수)
			for(int i=0; i<N; i++) {
				human[i][0] = sc.nextInt();
				human[i][1] = sc.nextInt();
				human[i][2] = sc.nextInt();
			}
			
			
			// 함정 수
			int trap = sc.nextInt();
			for(int i=0; i<trap; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				score[x][y] = 0;
			}
			
			
			for(int i=0; i<N; i++) {
				
				money -= 1000;
				
				int now_i = human[i][0];	// 시작위치
				int now_j = human[i][1];	// 시작위치
				int num   = human[i][2];	// 점프횟수
				int cnt = 0;
				
				
				for(int j=0; j<num; j++) {
					
					if(direction[now_i][now_j]==1) {
						now_j +=jump[now_i][now_j];
					}
					else if(direction[now_i][now_j]==2) {
						now_i +=jump[now_i][now_j];
					}
					else if(direction[now_i][now_j]==3) {
						now_j -=jump[now_i][now_j];
					}
					else if(direction[now_i][now_j]==4) {
						now_i -=jump[now_i][now_j];
					}
						
					// 경계 벗어나거나 함정 빠지면 break;
					if(now_i<=0 || now_j<=0 || now_i>X || now_j>Y || score[now_i][now_j]==0) {
						break;
					}
					
					cnt++;
				}
				if(cnt == num) {
					money += score[now_i][now_j]*100;
				}
				
			}
			
			
			System.out.println("#"+t+" "+money);
		}
		
	}
}






