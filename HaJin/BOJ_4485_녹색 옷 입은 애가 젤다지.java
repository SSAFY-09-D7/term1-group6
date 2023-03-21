import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N;
	public static int[][] arr;
	public static boolean[][] visited;
	public static int MIN;
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};
	
	public static class Node implements Comparable<Node>{
		int i;
		int j;
		int cnt;
		Node(int i, int j, int cnt){
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
       
        int test_case = 0;
        
        while(true) {
        	test_case += 1;
        	
        	N = Integer.parseInt(br.readLine());
        	
        	if(N == 0) {
        		break;
        	}
        	
        	visited = new boolean[N][N];
        	arr = new int[N][N];
        	MIN = Integer.MAX_VALUE;
        	
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			arr[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	func(0, 0);
        	
        	System.out.printf("Problem %d: %d\n", test_case, MIN);
        	
        }
     
    }
    
    public static void func(int i, int j) {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	
    	pq.add(new Node(i, j, arr[i][j]));
    	
    	
    	while(!pq.isEmpty()) {
    		Node node = pq.poll();
    		int ii = node.i;
    		int jj = node.j;
    		
    		if(ii == N-1 && jj == N-1) {
    			if(node.cnt < MIN) {
    				MIN = node.cnt;
    			}
    		}
    		
    		if(!visited[ii][jj]) {
    		
	    		visited[ii][jj] = true;
                
	    		for(int p=0; p<4; p++) {
	    			int iii = ii + nr[p];
	    			int jjj = jj + nc[p];
	    			
					if(iii>=0 && iii<N && jjj>=0 && jjj<N) {
	    				pq.add(new Node(iii, jjj, node.cnt + arr[iii][jjj]));
	    			}
	    		}
    		}
    		
    	}

    }
    
}