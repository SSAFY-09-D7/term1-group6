import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

	public static int N;
	public static int[][] arr;
	public static int MAX = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] > MAX)
					MAX = arr[i][j];
			}
		}
		
		func(0, arr);

		System.out.println(MAX);
	}

	public static void func(int cnt, int[][] arr) {
		
		if(cnt == 5) {
			return;
		}
		
		int[][] newArr = new int[N][N];
		Stack<Integer> stack = new Stack<>();
	
		// 상 : 아래부터 스택에 넣고 위부터 채우기
		for(int i=0; i<N; i++) {
			
			for(int j=N-1; j>=0; j--) {
				if(arr[j][i] != 0) {
					stack.add(arr[j][i]);
				}
			}
			
			for(int j=0; j<N; j++) {
				
				int a = 0;
				int b = 0;
				
				if(stack.size() == 0) {
					newArr[j][i] = 0;	
				}
				else if(stack.size() == 1) {
					newArr[j][i] = stack.pop();
				}
				
				else {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						newArr[j][i] = a * 2;
						if(a * 2 > MAX) {
							MAX = a * 2;
						}
					}
					else {
						newArr[j][i] = a;
						stack.add(b);
					}
				}
			}
		}
		
		func(cnt+1, newArr);
		
		// 하 : 위부터 스택에 넣고 아래부터 채우기
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<N; j++) {
				if(arr[j][i] != 0) {
					stack.add(arr[j][i]);
				}
			}
			
			for(int j=N-1; j>=0; j--) {
				
				int a = 0;
				int b = 0;
				
				if(stack.size() == 0) {
					newArr[j][i] = 0;
				}
				else if(stack.size() == 1) {
					newArr[j][i] = stack.pop();
				}
				
				else {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						newArr[j][i] = a * 2;
						if(a * 2 > MAX) {
							MAX = a * 2;
						}
					}
					else {
						newArr[j][i] = a;
						stack.add(b);
					}
				}
			}
		}
		
		func(cnt+1, newArr);
		
		// 좌 : 오른쪽부터 스택에 넣고 왼쪽부터 채우기
		for(int i=0; i<N; i++) {
			
			for(int j=N-1; j>=0; j--) {
				if(arr[i][j] != 0) {
					stack.add(arr[i][j]);
				}
			}
			
			for(int j=0; j<N; j++) {
				int a = 0;
				int b = 0;
				
				if(stack.size() == 0) {
					newArr[i][j] = 0;
				}
				else if(stack.size() == 1) {
					newArr[i][j] = stack.pop();
				}
				
				else {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						newArr[i][j] = a * 2;
						if(a * 2 > MAX) {
							MAX = a * 2;
						}
					}
					else {
						newArr[i][j] = a;
						stack.add(b);
					}
				}
				
			}
		}
		func(cnt+1, newArr);
		
		// 우 : 왼쪽부터 스택에 넣고 오른쪽부터 채우기
		for(int i=0; i<N; i++) {
			
			for(int j=0; j<N; j++) {
				if(arr[i][j] != 0) {
					stack.add(arr[i][j]);
				}
			}
			
			for(int j=N-1; j>=0; j--) {
				int a = 0;
				int b = 0;
				
				if(stack.size() == 0) {
					newArr[i][j] = 0;
				}
				else if(stack.size() == 1) {
					newArr[i][j] = stack.pop();
				}
				
				else {
					a = stack.pop();
					b = stack.pop();
					
					if(a == b) {
						newArr[i][j] = a * 2;
						if(a * 2 > MAX) {
							MAX = a * 2;
						}
					}
					else {
						newArr[i][j] = a;
						stack.add(b);
					}
				}
				
			}
		}
		func(cnt+1, newArr);
	}

}