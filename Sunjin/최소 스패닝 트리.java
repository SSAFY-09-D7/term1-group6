package algorithm;

import java.io.*;
import java.util.*;

public class Solution{
	static int V, E;
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
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
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> edges = new ArrayList<>();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges.add(new Edge(from, to, cost));
			}
			
			Collections.sort(edges);
			
			makeSet();
			long sum = 0;
			int count = 0;
			for(int i = 0; i < edges.size(); i++) {
				Edge edge = edges.get(i);
				if(unionSet(edge.from, edge.to)) {
					count++;
					sum += edge.cost;
					if(count == V - 1) break;
				}
			}
			
			System.out.println("#" + testCase + " " + sum);
		}
	}

	private static boolean unionSet(int from, int to) {
		int rootA = findRoot(from);
		int rootB = findRoot(to);
		
		if(rootA == rootB) return false;
		else {
			parents[rootB] = rootA;
			return true;
		}
	}

	private static int findRoot(int value) {
		if(parents[value] == value) return value;
		else return parents[value] = findRoot(parents[value]);
	}

	private static void makeSet() {
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
}