import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static int V, E, K;
	public static boolean[] visited;	// V+1
	public static int[] dist;			// V+1
	public static List<Node>[] arr;
	
	public static class Node implements Comparable<Node>{
		int node;
		int dist;
		
		Node(int node, int dist){
			this.node = node;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
       
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        visited = new boolean[V+1];
        dist = new int[V+1];
        arr = new ArrayList[V+1];
        
        // dist 초기화
        for(int i=0; i<V+1; i++) {
        	dist[i] = Integer.MAX_VALUE;
        }
        // arr 초기화
        for(int i=0; i<V+1; i++) {
        	arr[i] = new ArrayList<>();
        }
        
        K = Integer.parseInt(br.readLine());
        
        int u, v, w;
        for(int i=0; i<E; i++) {
        	st = new StringTokenizer(br.readLine());
        	u = Integer.parseInt(st.nextToken());
        	v = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	
        	arr[u].add(new Node(v, w));
        }
        
        Dijkstra(K);
        
        for(int i=1; i<=V; i++) {
        	if(dist[i] == Integer.MAX_VALUE)
        		System.out.println("INF");
        	else
        		System.out.println(dist[i]);
        }
	}
	
	public static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		dist[start] = 0;
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int nowNode = node.node;
			
			if(visited[nowNode]) {
				continue;
			}
			
			visited[nowNode] = true;
			
			for(Node n : arr[nowNode]) {
				if(dist[n.node] > dist[nowNode] + n.dist) {
					dist[n.node] = dist[nowNode] + n.dist;
					pq.add(new Node(n.node, dist[n.node]));
				}	
			}
		}
	}
}