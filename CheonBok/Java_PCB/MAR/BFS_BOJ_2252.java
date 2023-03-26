import java.io.*;
import java.util.*;

// BOJ 2252 줄 세우기
public class BFS_BOJ_2252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] conn = new int[N+1];  // in-degree Array
		
		// Graph Init
		List<Integer>[] nodes = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<Integer>();
		}
		
		int s, e;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); // 선행 (pre)
			e = Integer.parseInt(st.nextToken()); // 후행 (suf)
			
			nodes[s].add(e);   // Graph Setting
			conn[e]++;         // in-degree Setting
		}
		
		// Topology Process
		Queue<Integer> q = new LinkedList<Integer>();
		for (int n = 1; n <= N; n++) {   // 시작 정점 추가
			if (conn[n] == 0) q.add(n);
		}
		
		while (!q.isEmpty()) {
			// 추출된 노드들은 모두 진입차수가 0이다 = 출력 대상
			int pos = q.poll();  
			sb.append(pos+" ");
			
			// 간선 제거 후 in-degree 0이면 출력 대상
			for(int next : nodes[pos]) {
				conn[next]--;
				
				if (conn[next] == 0) q.add(next);
			}
		}
		System.out.println(sb);
	}
}