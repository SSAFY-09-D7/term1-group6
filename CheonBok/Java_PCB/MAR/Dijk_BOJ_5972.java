import java.io.*;
import java.util.*;
public class Dijk_BOJ_5972 {
	
	static class node implements Comparable<node>{
		int dist, next;
		public node(int dist, int next) {
			this.dist = dist;
			this.next = next;
		}
		
		@Override
		public int compareTo(node o) {
			return this.dist - o.dist;
		}

		@Override
		public String toString() {
			return "node [dist=" + dist + ", next=" + next + "]";
		}
	}
	
	static int N, M;
	static ArrayList<node>[] map;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			map[i] = new ArrayList<node>();
		}
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[a].add(new node(d, b));
			map[b].add(new node(d, a));
		}
		
		PriorityQueue<node> q = new PriorityQueue();
		dist[1] = 0;
		q.add(new node(0, 1)); 
		
		boolean[] visited = new boolean[N+1]; 
		while (!q.isEmpty()) {
			node pos = q.poll();
			if (pos.next == N) break;
			
			visited[pos.next] = true;
			
			for(node n: map[pos.next]) {
				//System.out.println(n);
				if (dist[n.next] > dist[pos.next] + n.dist && !visited[n.next]) {
					dist[n.next] = dist[pos.next] + n.dist;
					q.add(new node(dist[n.next], n.next));
				}
			}
		}
		//System.out.println(Arrays.toString(dist));
		System.out.println(dist[N]);
	}

}
