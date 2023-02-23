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

public class Main{
	
	public static int R, C;
	public static int[][] arr;
	public static boolean[] visited = new boolean[27];
	public static int MAX = 0;
	
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};

	public static class Node{
		int i;
		int j;
		int cnt;
		
		Node(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
	
	public static void func(int i, int j, int cnt) {
		
		if(cnt > MAX)
			MAX = cnt;
		
		for(int p=0; p<4; p++) {
			int ii = i + nr[p];
			int jj = j + nc[p];
			
			if(ii>=0 && ii<R && jj>=0 && jj<C && !visited[arr[ii][jj]]) {
				visited[arr[ii][jj]] = true;
				func(ii, jj, cnt+1);
				visited[arr[ii][jj]] = false;
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	
	    st = new StringTokenizer(br.readLine());
	    R = Integer.parseInt(st.nextToken());
	    C = Integer.parseInt(st.nextToken());
	    
	    arr = new int[R][C];
	    
	    for(int i=0; i<R; i++) {
	    	String s = br.readLine();
	    	for(int j=0; j<C; j++) {
	    		arr[i][j] = s.charAt(j) - 65;
	    	}
	    }
	    
	    visited[arr[0][0]] = true;
	    func(0, 0, 1);
	    
	    System.out.println(MAX);
	}

}