package com.ssafy.a_algorithm;

import java.util.*;

public class Algorithm {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int test_case;
		test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			
			int n;
			char[][] arr = new char[25][25];
			int ans = 0;
			
			// 25*25 'B'로 초기화
			for(int i=0; i<25; i++) {
				for(int j=0; j<25; j++) {
					arr[i][j] = 'B';
				}
			}
			
			n = sc.nextInt();
			
			// (1,1) 부터 시작
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					arr[i][j] = sc.next().charAt(0);
				}
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					
					int cnt = 0;
					int tmp_height=0;
					
					if(arr[i][j] == 'B') {
						for(int a=i-1; a<=i+1; a++) {
							for(int b=j-1; b<=j+1; b++) {
								if(arr[a][b]=='B')
									cnt++;
							}
						}
						
						// cnt = 9 이면 2층 이상 빌딩 가능 구역
						if(cnt==9) {
							for(int a=1; a<=n; a++) {
								if(arr[i][a] == 'B')
									tmp_height++;
							}
							for(int a=1; a<=n; a++) {
								if(arr[a][j] == 'B')
									tmp_height++;
							}
							
						}
						
						if(tmp_height > ans)
							ans = tmp_height;
					}
				
				}
			}
			
			if(ans == 0)
				ans = 2;
			else
				ans -= 1;
			
			System.out.println("#"+t+" "+ans);
		}
		
	}
	

}






