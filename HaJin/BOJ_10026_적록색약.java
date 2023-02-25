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
	
	public static int N;
	public static char[][] arr;
	public static boolean[][] visited;
	
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	
	public static int cnt1 = 0;
	public static int cnt2 = 0;
	
	public static class Node{
		int i;
		int j;
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	public static void func(int i, int j) {
		
		visited[i][j] = true;
		char c = arr[i][j];
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i, j));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int ii = node.i;
			int jj = node.j;
			
			for(int p = 0; p < 4; p++) {
				int iii = ii + nr[p];
				int jjj = jj + nc[p];
				
				if(iii>=0 && iii<N && jjj>=0 && jjj<N && !visited[iii][jjj] && arr[iii][jjj]==c) {
					visited[iii][jjj] = true;
					queue.add(new Node(iii, jjj));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	    
	    N = Integer.parseInt(br.readLine());
	    
	    arr = new char[N][N];
	    visited = new boolean[N][N];
	    
	    for(int i=0; i<N; i++) {
	    	String s = br.readLine();
	    	for(int j=0; j<N; j++)
	    		arr[i][j] = s.charAt(j);
	    }
	    
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		if(!visited[i][j] ) {
	    			cnt1 += 1;
	    			func(i, j);
	    		}
	    	}
	    }
	    
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		if(arr[i][j] == 'G')
	    			arr[i][j] = 'R';
	    	}
	    }
	    
	    visited = new boolean[N][N];
	    
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		if(!visited[i][j] ) {
	    			cnt2 += 1;
	    			func(i, j);
	    		}
	    	}
	    }
	    
	    System.out.printf("%d %d", cnt1, cnt2);
	    
	}
}