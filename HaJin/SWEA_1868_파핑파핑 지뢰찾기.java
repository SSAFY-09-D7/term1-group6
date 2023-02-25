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
	
	public static int T, N;
	public static char[][] arr;
	public static boolean[][] visited;
	
	public static int[] nr = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int[] nc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static Queue<Node> queue2;
	
	public static int cnt = 0;

	public static class Node{
		int i;
		int j;
		
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static void func(int x, int y) {
			
        for(int i=0; i<8; i++) {
            int xx = x + nr[i];
            int yy = y + nc[i];

            if(xx>=0 && xx<N && yy>=0 && yy<N && !visited[xx][yy]) {
                arr[xx][yy] = '0';
                visited[xx][yy] = true;

                if(isPop(xx, yy))
                    func(xx, yy);
            }
        }
	}
	
	public static boolean isPop(int x, int y) {
		
		for(int i=0; i<8; i++) {
			int xx = x + nr[i];
			int yy = y + nc[i];
			
			if(xx>=0 && xx<N && yy>=0 && yy<N) {
				if(arr[xx][yy] == '*')
					return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	
	    T = Integer.parseInt(br.readLine());
	    
	    for(int test_case = 1; test_case <= T; test_case++) {
	    	
	    	cnt = 0;
	    	
	    	N = Integer.parseInt(br.readLine());
	    	arr = new char[N][N];
	    	visited = new boolean[N][N];
	    	
	    	for(int i=0; i<N; i++) {
	    		String s = br.readLine();
	    		for(int j=0; j<N; j++) {
	    			arr[i][j] = s.charAt(j);
	    		}
	    	}
	    	
	    	for(int i=0; i<N; i++) {
	    		for(int j=0; j<N; j++) {
	    			if(arr[i][j] == '.' && isPop(i, j)) {
	    				visited[i][j] = true;
	    				cnt += 1;
	    				arr[i][j] = '0';
	    				func(i, j);
	    			}
	    		}
	    	}
            
	    	// pop 모두 확인 후 나머지 더하기
	    	for(int i=0; i<N; i++) {
	    		for(int j=0; j<N; j++) {
	    			if(arr[i][j] == '.')
	    				cnt += 1;
	    		}
	    	}
	    	
	    	System.out.printf("#%d %d\n", test_case, cnt);
	    }
	}
}