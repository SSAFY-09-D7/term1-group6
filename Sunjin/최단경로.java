package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int V, E;
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Edge>[] list;
	static class Edge implements Comparable<Edge>{
		int to, cost;
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        int start = Integer.parseInt(br.readLine());
        
        list = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
        	list[i] = new ArrayList<>();
        }
        
        visited = new boolean[V + 1];
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	list[from].add(new Edge(to, cost));
        }
        
        distance = new int[V + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(start, 0));
        
        while(!q.isEmpty()) {
        	Edge now = q.poll();
        	
        	if(!visited[now.to]) {
        		visited[now.to] = true;	
        	}
        	
        	for(Edge next : list[now.to]) {
        		if(distance[now.to] + next.cost < distance[next.to]) {
        			distance[next.to]= distance[now.to] + next.cost; 
        			q.add(new Edge(next.to, distance[next.to]));
        		}
        	}
        }
        
        for(int i = 1; i <= V; i++) {
        	if(distance[i] == INF) System.out.println("INF");
        	else System.out.println(distance[i]);
        }
    }
}
