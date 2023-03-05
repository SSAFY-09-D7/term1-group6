package algorithm;

import java.io.*;
import java.util.*;

public class Main2 {
	static int N;
	static int min;
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;
	static boolean[] visited;
	static int[][] map;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static ArrayList<Node>[] list;
	static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int T = Integer.parseInt(br.readLine());
    	for(int testCase = 1; testCase <= T; testCase++) {
    		N = Integer.parseInt(br.readLine());
    		map = new int[N][N];
    		
    		for(int i = 0; i < N; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < N; j++) {
    				map[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		list = new ArrayList[N * N];
    		for(int i = 0; i < N * N; i++) {
    			list[i] = new ArrayList<>();
    		}
    		
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < N; j++) {
    				drawGraph(i, j);
    			}
    		}
    		
    		distance = new int[N * N];
    		visited = new boolean[N * N];
    		
    		Arrays.fill(distance, INF);
    		distance[0] = 0;
    		
    		dijkstra(0);
    		
    		System.out.println("#" + testCase + " " + distance[N * N - 1]);
    	}
	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(visited[now.to]) continue;
			
			visited[now.to] = true;
			
			for(Node next : list[now.to]) {
				if(!visited[next.to] && distance[now.to] + next.cost < distance[next.to]) {
					distance[next.to]= distance[now.to]+ next.cost;
					q.add(new Node(next.to, distance[next.to]));
				}
			}
		}
		
	}
	private static void drawGraph(int row, int col) {
		int from = N * row + col;
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
			
			int to = N * nextRow + nextCol;
			int cost = 0;
			if(map[row][col] == map[nextRow][nextCol]) cost = 1;
			if(map[row][col] < map[nextRow][nextCol]) cost = (map[nextRow][nextCol] - map[row][col]) * 2;
			
			list[from].add(new Node(to, cost));
		}
	}
}