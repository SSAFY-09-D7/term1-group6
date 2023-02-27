import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static int T, N, M;
	public static StringBuilder sb;
	public static int[] parent;
	
	public static int find(int a) {
		if(parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		int aa = find(a);
		int bb = find(b);
		
		if(aa != bb) {
			parent[bb] = aa;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        
        T = Integer.parseInt(br.readLine());
        
        for(int test_case=1; test_case<=T; test_case++) {
        	
        	sb = new StringBuilder();
        	
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	
        	parent = new int[N+1];
        	for(int i=1; i<N+1; i++) {
        		parent[i] = i;
        	}
        	
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int p = Integer.parseInt(st.nextToken());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		
        		if(p == 0) {
        			if(a<b)
        				union(a, b);
        			else
        				union(b, a);
        		}
        		
        		else if(p == 1) {
        			if(find(a) == find(b))
        				sb.append("1");
        			else
        				sb.append("0");
        		}
        	}
        	System.out.printf("#%d ", test_case);
        	System.out.println(sb);
        }
	}

}
