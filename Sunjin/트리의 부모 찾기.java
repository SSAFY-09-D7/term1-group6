import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] parents;
	static boolean[] visited;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		parents = new int[N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		
		bfs(1);
		for(int i = 2; i <= N; i++) {
			System.out.println(parents[i]);
		}
	}
	private static void bfs(int cur) {
		Queue<Integer> q = new LinkedList<>();
		visited[cur] = true;
		q.add(cur);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : list[now]) {
				if(!visited[next]) {
					parents[next] = now;
					visited[next] = true;
					q.add(next);
				}
			}
		}
	}

}
