import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r;
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;
	static boolean[] visited;
	static int[] items;
	static ArrayList<Node>[] list;
	static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
	
		items = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, cost));
			list[to].add(new Node(from, cost));
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i <= n; i++) {
			distance = new int[n + 1];
			visited = new boolean[n + 1];
			Arrays.fill(distance, INF);
			distance[i] = 0;
			
			PriorityQueue<Node> q = new PriorityQueue<>();
			q.add(new Node(i, 0));
			
			while(!q.isEmpty()) {
				Node now = q.poll();
				
				if(visited[now.to]) continue;
				visited[now.to]= true;
				
				for(Node next : list[now.to]) {
					if(distance[now.to] + next.cost < distance[next.to]) {
						distance[next.to] = distance[now.to] + next.cost;
						q.add(new Node(next.to, distance[next.to]));
					}
				}
			}
			
			int sum = 0;
			for(int j = 1; j <= n; j++) {
				if(distance[j] <= m) {
					sum += items[j];
				}
			}
			if(max < sum) max = sum;
		}
		System.out.println(max);
	}
}