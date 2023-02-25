import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{

	public static int T, H, W, N;
	public static int now_i, now_j, now_d;	// 1:up  2:down  3:left  4:right
	public static char[][] arr;

	public static void main(String[] args) throws IOException{
	
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	
	    st = new StringTokenizer(br.readLine());
	    T = Integer.parseInt(st.nextToken());
	
	    for(int test_case = 1; test_case <= T; test_case++){
	
	    	st = new StringTokenizer(br.readLine());
	    	H = Integer.parseInt(st.nextToken());
	    	W = Integer.parseInt(st.nextToken());
	    	
	    	arr = new char[H][W];
	        for(int i=0; i<H; i++) {
	        	String s = br.readLine();
	        	for(int j=0; j<W; j++) {
	        		arr[i][j] = s.charAt(j);
	        		if(arr[i][j] == '^') {
	        			now_i = i;
	        			now_j = j;
	        			now_d = 1;
	        		}
	        		else if(arr[i][j] == 'v') {
	        			now_i = i;
	        			now_j = j;
	        			now_d = 2;
	        		}
	        		else if(arr[i][j] == '<') {
	        			now_i = i;
	        			now_j = j;
	        			now_d = 3;
	        		}
	        		else if(arr[i][j] == '>') {
	        			now_i = i;
	        			now_j = j;
	        			now_d = 4;
	        		}
	        	}
	        }
	        
	        st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        
	        String s = br.readLine();
	        
	        for(int i=0; i<s.length(); i++) {
	        	char c = s.charAt(i);
	        	
	        	if(c == 'U') {
	        		now_d = 1;
	        		arr[now_i][now_j] = '^';
	        		if(now_i-1 >= 0 && arr[now_i-1][now_j] == '.') {
	        			arr[now_i][now_j] = '.';
	        			arr[now_i-1][now_j] = '^';
	        			now_i -= 1;
	        		}
	        	}
	        	else if(c == 'D') {
	        		now_d = 2;
	        		arr[now_i][now_j] = 'v';
	        		if(now_i+1 < H && arr[now_i+1][now_j] == '.') {
	        			arr[now_i][now_j] = '.';
	        			arr[now_i+1][now_j] = 'v';
	        			now_i += 1;
	        		}        		
				}
	        	else if(c == 'L') {
	        		now_d = 3;
	        		arr[now_i][now_j] = '<';
	        		if(now_j-1 >= 0 && arr[now_i][now_j-1] == '.') {
	        			arr[now_i][now_j] = '.';
	        			arr[now_i][now_j-1] = '<';
	        			now_j -= 1;
	        		}
					
				}
	        	else if(c == 'R') {
	        		now_d = 4;
	        		arr[now_i][now_j] = '>';
	        		if(now_j+1 < W && arr[now_i][now_j+1] == '.') {
	        			arr[now_i][now_j] = '.';
	        			arr[now_i][now_j+1] = '>';
	        			now_j += 1;
	        		}
				}
	        	else if(c == 'S') {
	        		int s_i = now_i;
	        		int s_j = now_j;
	        		// up
					if(now_d == 1) {
						while(true) {
							if(s_i-1 < 0)
								break;
							else if(arr[s_i-1][s_j] == '*') {
								arr[s_i-1][s_j] = '.';
								break;
							}
							else if(arr[s_i-1][s_j] == '#') {
								break;
							}
							else {
								s_i -= 1;
							}
						}
						
					}
					else if(now_d == 2) {
						while(true) {
							if(s_i+1 >= H)
								break;
							else if(arr[s_i+1][s_j] == '*') {
								arr[s_i+1][s_j] = '.';
								break;
							}
							else if(arr[s_i+1][s_j] == '#') {
								break;
							}
							else {
								s_i += 1;
							}
						}
						
					}
					else if(now_d == 3) {
						while(true) {
							if(s_j-1 < 0)
								break;
							else if(arr[s_i][s_j-1] == '*') {
								arr[s_i][s_j-1] = '.';
								break;
							}
							else if(arr[s_i][s_j-1] == '#') {
								break;
							}
							else {
								s_j -= 1;
							}
						}
					}
					else if(now_d == 4) {
						while(true) {
							if(s_j+1 >= W)
								break;
							else if(arr[s_i][s_j+1] == '*') {
								arr[s_i][s_j+1] = '.';
								break;
							}
							else if(arr[s_i][s_j+1] == '#') {
								break;
							}
							else {
								s_j += 1;
							}
						}
					}
				}
	        }
	        
	        System.out.printf("#%d ", test_case);
	        
	        for(int i=0; i<H; i++) {
	        	for(int j=0; j<W; j++) {
	        		System.out.print(arr[i][j]);
	        	}
	        	
	        	System.out.println();
	        }
	        
	    }
	
	}
}