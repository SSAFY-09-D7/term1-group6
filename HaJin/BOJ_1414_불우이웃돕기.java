import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static int N;
	public static List<Node> list;
	public static int[] parent;
	public static class Node implements Comparable<Node>{
		int node1;
		int node2;
		int dist;
		
		Node(int node1, int node2, int dist){
			this.node1 = node1;
			this.node2 = node2;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	public static int find(int a) {
		if(parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		int pa = parent[a];
		int pb = parent[b];
		
		if(pa != pb) {
			if(pa < pb) {
				parent[pb] = parent[pa];
			}
			else
				parent[pa] = parent[pb];
		}
	}
	
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
       
        N = Integer.parseInt(br.readLine());
        
        list = new ArrayList<>();
        
        for(int i=1; i<=N; i++) {
        	String s = br.readLine();
        	for(int j=1; j<=N; j++) {
        		char a = s.charAt(j-1);
        		
        		if(a != '0') {
        			if(a >= 'a' && a <= 'z') {
        				list.add(new Node(i, j, a-'0'-48));
        			}
        			else {
        				list.add(new Node(i, j, a-'0'+10));
        			}
        		}
        	}
        }
        
        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
        	parent[i] = i;
        }
        
        Collections.sort(list);
        
        int cnt = 0;
        int ans = 0;
        
        for(Node n : list) {
        	if(find(n.node1) != find(n.node2)) {
        		union(n.node1, n.node2);
        		cnt += 1;
        	}
        	else {
        		ans += n.dist;
        	}
        }
        
        if(cnt == N-1) {
        	System.out.println(ans);
        }
        else
        	System.out.println("-1");
        
    }
    
    //a - '0'    49
    //A - '0'    17
}