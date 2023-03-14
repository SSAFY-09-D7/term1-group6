import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int s, e;
	static final int INF = Integer.MIN_VALUE;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Node>[] list;
	static class Node implements Comparable<Node>{
		int to, limit;
		public Node(int to, int limit) {
			this.to = to;
			this.limit = limit;
		}
		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return o.limit - this.limit;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		list = new ArrayList [N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, limit));
			list[to].add(new Node(from, limit));
		}
		
		visited = new boolean[N + 1];
		
		int max = Integer.MAX_VALUE;
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(s, 0));
		boolean flag = false;
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(visited[now.to]) continue;
			visited[now.to] = true;
			
			if(now.limit < max && now.to != s) max = now.limit;
			
			if(now.to == e) {
				flag = true;
				break;
			}
			
			for(Node next : list[now.to]) {
				if(!visited[next.to]) {
					q.add(next);
				}
			}
		}
		
		if(flag) System.out.println(max);
		else System.out.println(0);
	}
}