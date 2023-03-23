import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N;
	public static List<Node>[] list;
	public static boolean[] visited;
	public static int MAX = 0;
	
	public static class Node{
		int node;
		int dist;
		
		Node(int node, int dist){
			this.node = node;
			this.dist = dist;
		}
	}
	
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
       
        N = Integer.parseInt(br.readLine());
        
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        int a, b, c;
        for(int i=0; i<N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	
        	list[a].add(new Node(b, c));
        	list[b].add(new Node(a, c));
        }
        
        visited = new boolean[N+1];
        
        for(int i=1; i<=N; i++) {
        	if(list[i].size() == 1) {
        		visited[i] = true;
        		dfs(i, 0);
        		visited[i] = false;
        	}
        }
        
        System.out.println(MAX);
   
    }
    
    public static void dfs(int node, int sum) {
    	
    	if(sum > MAX) {
    		MAX = sum;
    	}
    	
    	for(int i=0; i<list[node].size(); i++) {
    		if(!visited[list[node].get(i).node]) {
    			visited[list[node].get(i).node] = true;
    			dfs(list[node].get(i).node, sum+list[node].get(i).dist);
    			visited[list[node].get(i).node] = false;
    		}
    	}
    }
    
}