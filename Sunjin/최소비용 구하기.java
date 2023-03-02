package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Edge>[] list;
	static class Edge{
		int to, cost;
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
        	list[i] = new ArrayList();
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	list[from].add(new Edge(to, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        
        Arrays.fill(distance, INF);
        distance[start] = 0;
        
        int current = 0, min = INF;
        for(int c = 1; c <= N; c++) {
        	
        	current = -1;
        	min = INF;
        	// 최솟값 찾기 과정
        	for(int i = 1; i <= N; i++) {
        		if(!visited[i] && distance[i] < min) {
        			current = i;
        			min = distance[i];
        		}
        	}
        	
        	if(current == end) break;
        	visited[current] = true;
        	
        	for(Edge edge : list[current]) {
        		if(!visited[edge.to] && min + edge.cost < distance[edge.to]) {
        			distance[edge.to] = min + edge.cost; 
        		}
        	}
        	
        }
        
        System.out.println(distance[end]);
    }
}
