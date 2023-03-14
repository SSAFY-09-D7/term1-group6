import java.io.*;
import java.util.*;
// 6497
public class Main {
	static int m, n;
	static int[] parents;
	static ArrayList<Edge> edges;
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Main.Edge o) {
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
			
			edges = new ArrayList<>();
			
			int originSum = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges.add(new Edge(from, to, cost));
				originSum += cost;
			}
//			크루스칼로 다시 풀어보기
			
			Collections.sort(edges);
			makeSet();
		
			int sum = 0;
			for(int i = 0; i < edges.size(); i++) {
				Edge edge = edges.get(i);
				int from = edge.from;
				int to = edge.to;
				if(unionSet(from, to)) {
					sum += edge.cost;
				}
			}
			System.out.println(originSum - sum);
		}

	}
	private static boolean unionSet(int a, int b) {
		int rootA = findRoot(a);
		int rootB = findRoot(b);
		
		if(rootA == rootB) return false;
		else {
			parents[rootA] = rootB;
			return true;
		}
	}
	private static int findRoot(int val) {
		if(parents[val] == val) return val;
		else return parents[val] = findRoot(parents[val]);
	}
	private static void makeSet() {
		parents = new int[m];
		for(int i = 0; i < m; i++) {
			parents[i] = i;
		}
	}
}