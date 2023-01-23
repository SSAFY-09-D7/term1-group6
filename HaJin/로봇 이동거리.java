package com.ssafy.a_algorithm;

import java.util.*;

public class Algorithm {
	
	public static int move_right(char arr[][], int i, int j, int n) {
		int cnt = 0;
		int now_i = i;
		int now_j = j;
		
		while(true) {
			if(now_j+1<n && arr[i][now_j+1] == 'S') {
				cnt++;
				now_j++;
			}
			else
				break;
		}
		
		return cnt;
	}
	
	public static int move_left(char arr[][], int i, int j, int n) {
		int cnt = 0;
		int now_i = i;
		int now_j = j;
		
		while(true) {
			if(now_j-1>=0 && arr[i][now_j-1] == 'S') {
				cnt++;
				now_j--;
			}
			else
				break;
		}
		
		return cnt;
	}
	
	public static int move_down(char arr[][], int i, int j, int n) {
		int cnt = 0;
		int now_i = i;
		int now_j = j;
		
		while(true) {
			if(now_i+1<n && arr[now_i+1][j] == 'S') {
				cnt++;
				now_i++;
			}
			else
				break;
		}
		
		return cnt;
	}
	
	
	public static int move_up(char arr[][], int i, int j, int n) {
		int cnt = 0;
		int now_i = i;
		int now_j = j;
		
		while(true) {
			if(now_i-1 >= 0 && arr[now_i-1][j] == 'S') {
				cnt++;
				now_i--;
			}
			else
				break;
		}
		
		return cnt;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int test_case;
		test_case = sc.nextInt();
		
		for(int t=1; t<=test_case; t++) {
			
			int n;
			char[][] arr = new char[25][25];
			int ans = 0;
			
			n = sc.nextInt();
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					arr[i][j] = sc.next().charAt(0);
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					
					int now_j = j;
					int now_i = i;
					
					if(arr[i][j] == 'A') {
						ans += move_right(arr, i, j, n);
					}
					else if(arr[i][j] == 'B') {
						ans += move_right(arr, i, j, n);
						ans += move_left(arr, i, j, n);
					}
					else if(arr[i][j] == 'C') {
						ans += move_right(arr, i, j, n);
						ans += move_left(arr, i, j, n);
						ans += move_down(arr, i, j, n);
						ans += move_up(arr, i, j, n);
					}
					
				}
			}
			
			System.out.println("#"+t+" "+ans);
			
			
		}

	}

}
