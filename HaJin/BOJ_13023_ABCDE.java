import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	
	public static int N, M;
	public static boolean[] visited;	// new [N]
	public static List<Integer>[] list;		// N
	public static boolean possible = false;
	
	public static void func(int i, int cnt) {

		if(cnt >= 5) {
			possible = true;
			return;
		}
		
		for(int p=0; p<list[i].size(); p++) {
			if(!visited[list[i].get(p)]) {
				visited[list[i].get(p)] = true;
				func(list[i].get(p), cnt+1);
				visited[list[i].get(p)] = false;
				
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st ;
	    
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    visited = new boolean[N];
	    list = new ArrayList[N];
	    for(int i=0; i<N; i++) {
	    	list[i] = new ArrayList<Integer>();
	    }
	    
	    for(int i=0; i<M; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken());
	    	int b = Integer.parseInt(st.nextToken());
	    	
	    	list[a].add(b);
	    	list[b].add(a);
	    }
	    
	    for(int i=0; i<N; i++) {
	    	if(list[i].size() != 0) {
	   
	    		visited[i] = true;
	    		func(i, 1);
	    		visited[i] = false;
	    		
	    		if(possible)
	    			break;
	    	}

	    }
	    
	    if(possible)
	    	System.out.println("1");
	    else
	    	System.out.println("0");
	}

}