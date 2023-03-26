import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEK_18352 {
	static int N, M, K, X;
	static int[] destination;
	static ArrayList<Integer>[] nodes;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // cities
		M = Integer.parseInt(st.nextToken()); // roads
		K = Integer.parseInt(st.nextToken()); // dest
		X = Integer.parseInt(st.nextToken()); // start Node
		
		nodes = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		destination = new int[N+1];  // start is 1
		Arrays.fill(destination, Integer.MAX_VALUE);
		destination[X] = 0; // start Node Initial
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			nodes[S].add(D);
		}
		
		
		boolean[] used = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(X);
		used[X] = true;  // 첫 정점 방문 처리
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next: nodes[current]) {
				
				//if (!used[next] && destination[next] > destination[current] + 1) {
				// 모든 간선의 가중치는 1이므로 마치 트리처럼 뻗어나가기만 함 (겹치지 않도록 방문 검사만)
				if (!used[next]) {
					used[next] = true;
					destination[next] = destination[current] + 1;
					queue.add(next);
				}
			}
		}
		
		//System.out.println(Arrays.toString(destination));
		// 하나라도 출력이 있으면 flg = true
		boolean flg = false;
		for (int idx = 1; idx < N+1; idx++) {
			if (destination[idx] == K) {
				System.out.println(idx);
				
				if (!flg) flg = true;
			}
		}
		if (!flg) System.out.println("-1");	
	}
}
