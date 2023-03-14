import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
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

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, cost));
		}
		
		Collections.sort(edges);
		makeSet();
		
		int count = 0;
		int result = 0;
		for(int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			int from = edge.from;
			int to = edge.to;
			
			if(unionSet(from, to)) {
				count++;
				result += edge.cost;
			}
			
			if(count == N - 2) break;
		}
		System.out.println(result);
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
		parents = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
}