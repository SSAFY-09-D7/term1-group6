import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Vertex>[] list;
	static class Vertex implements Comparable<Vertex>{
		int to, cost;
		public Vertex(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.cost, o.cost);
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
        	Vertex vertex = new Vertex(to, cost);
        	list[from].add(vertex);
        	vertex = new Vertex(from, cost);
        	list[to].add(vertex);
         }
        
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        PriorityQueue<Vertex> q = new PriorityQueue<>();
        
        q.add(new Vertex(1, 0));
        
        int sum = 0;
        while(!q.isEmpty()) {
        	Vertex now = q.poll();
        	
        	if(!visited[now.to]) {
//        		System.out.print(now.to + " ");
        		sum += now.cost;
        		visited[now.to] = true;
        	}
        	
        	for(Vertex next : list[now.to]) {
        		if(!visited[next.to] && next.cost < distance[next.to]) {
        			distance[next.to] = next.cost;
        			q.add(next);
        		}
        	}
        }
        
        System.out.println(sum);
	}
}