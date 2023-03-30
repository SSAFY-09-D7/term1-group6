// BOJ 1260 DFS와 BFS
import java.io.*;
import java.util.*;
public class BAEK_1260 {
	
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] Nodes; // 인접리스트
	static boolean[] dfsused;          // dfs에서 사용할 방문처리
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        
        Nodes = new ArrayList[N+1];    // 각 노드에 대해 인접한 노드의 정보를 담는 인접리스트
        for (int i = 1; i < N+1; i++) {
			Nodes[i] = new ArrayList<>();
		}
        
        // 테스트 케이스2에서 양방향 간선인 것을 알 수 있다.
        // 갔던 노드는 다시 갈 수 없도록 방문 처리가 필요하다.
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			Nodes[A].add(B);
			Nodes[B].add(A);
		}
        
        // 방문할 수 있는 정점이 여러 개라면 번호가 작은 것을 우선한다고 했다. - 정렬
        for (int i = 1; i <N+1 ; i++) {
			Nodes[i].sort(null);
		}
        
        
        /// V = 시작 정점
        dfsused = new boolean[N+1];
        dfs(V);
        sb.append("\n");
        bfs(V);
        
        System.out.println(sb);
      
	}

	// dfs : 재귀 형태로 구현
	// 특정 정점에 방문하면 가장 작은 번호의 인접 정점으로 들어가 깊이 우선 탐색
	private static void dfs(int node) {
		
		sb.append(node+" ");
		dfsused[node] = true;
		
		for (int i = 0; i < Nodes[node].size(); i++) {
			int tmp = Nodes[node].get(i);
			
			if (!dfsused[tmp]) dfs(tmp);
		}
		
	}

	
	// bfs : Queue를 이용해 구현
	// 특정 노드와 연결된 모든 노드를 queue에 담아버리고 하나씩 poll.
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] used = new boolean[N+1]; 
		used[start] = true;   // 이미 사용한 노드는 거르기
		sb.append(start+" ");
		
		// 특정 정점에 연결된 모든 노드를 queue에 담는다.
		for (int i = 0; i < Nodes[start].size(); i++) {
			queue.add(Nodes[start].get(i));
		}
		
		//System.out.println(queue);
		
		while (!queue.isEmpty()) {
			int recent = queue.poll();
			
			// 이미 방문한 정점이라면 넘긴다. (이미 큐에 들어온 정점 처리)
			if (used[recent]) continue; 
			
			used[recent] = true;
			sb.append(recent+" ");
			
			if (!Nodes[recent].isEmpty()) {
				
				
				// 특정 정점에 연결된 모든 정점 중 방문하지 않은 정점만 queue에 담는다.
				for (int i = 0; i < Nodes[recent].size(); i++) {
					int tmp = Nodes[recent].get(i);
					
					if (!used[tmp]) {
						queue.add(tmp);
					}
				}
			}
		}
	}
}