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
	
	public static int T, N, M;
	public static int[] parent;
	
	public static int find(int a) {
		if(parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b)
			parent[b] = a;
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	
	    T = Integer.parseInt(br.readLine());
	    
	    for(int test_case = 1; test_case <= T; test_case++) {
	    	
	    	st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken());
	    	M = Integer.parseInt(st.nextToken());
	    	
	    	parent = new int[N+1];
	    	
	    	for(int i=1; i<=N; i++) {
	    		parent[i] = i;
	    	}
	    	
	    	for(int i=0; i<M; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken());
	    		int b = Integer.parseInt(st.nextToken());
	    		
	    		union(a, b);
	    	}
	    
	    	boolean[] b = new boolean[N+1];
	    	
	    	for(int i=1; i<=N; i++) {
	    		b[find(i)] = true;
	    	}
	    	
	    	int cnt = 0;
	    	for(int i=1; i<=N; i++) {
	    		if(b[i])
	    			cnt+=1;
	    	}
	    	
	    	System.out.printf("#%d %d\n", test_case, cnt);

	    }
	}
}