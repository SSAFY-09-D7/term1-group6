import java.io.*;
import java.util.*;
// 6497
public class Main {
	static int m, n;
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;
	static boolean[] visited;
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
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if(m == 0 && n == 0) break;
			
			list = new ArrayList[m];
			for(int i = 0; i < m; i++) {
				list[i] = new ArrayList<>();
			}
			
			visited = new boolean[m];
			int originSum = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list[from].add(new Node(to, cost));
				list[to].add(new Node(from, cost));
				originSum += cost;
			}
			
			int sum = 0;
			distance = new int[m];
			Arrays.fill(distance, INF);
			distance[0] = 0;
			PriorityQueue<Node> q = new PriorityQueue<>();
			
			q.add(new Node(0, 0));
			while(!q.isEmpty()) {
				Node now = q.poll();
				
				if(visited[now.to]) continue;
				visited[now.to] = true;
				sum += now.cost;
				
				for(Node next : list[now.to]) {
					if(!visited[next.to]) {
						q.add(next);
					}
				}
			}
			
			
			System.out.println(originSum - sum);
		}

	}
}