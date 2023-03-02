package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static final Long INF = Long.MAX_VALUE;
    static int[] canSee;
    static long[] distance;
    static boolean[] visited;
    static ArrayList<Node>[] list;
    
    static class Node implements Comparable<Node>{
    	int to;
    	long cost;
    	public Node(int to, long cost) {
    		this.to = to;
    		this.cost = cost;
    	}
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
    }
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        canSee = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	canSee[i] = Integer.parseInt(st.nextToken());
        }
        canSee[0] = canSee[N - 1] = 0;
        
        list = new ArrayList[N];
        for(int i = 0; i < N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	list[from].add(new Node(to, cost));
        	list[to].add(new Node(from, cost));
        }
        
        distance = new long[N];
        visited = new boolean[N];
        
        Arrays.fill(distance, INF);
        distance[0] = 0;
        
        dijkstra(0, 0);
        
        if(distance[N - 1] == INF) System.out.println(-1);
        else System.out.println(distance[N - 1]);
	}
	
	private static void dijkstra(int to, int cost) {
		PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(to, cost));
        
        while(!q.isEmpty()) {
        	Node now = q.poll();
        	
        	if(visited[now.to]) continue;
        	else visited[now.to] = true;
        	
        	for(Node next : list[now.to]) {
        		if(!visited[next.to] && distance[now.to] + next.cost < distance[next.to] && canSee[next.to] == 0) {
        			distance[next.to] =  distance[now.to] + next.cost;
        			q.add(new Node(next.to, distance[next.to]));
        		}
        	}
        }
	}
}
