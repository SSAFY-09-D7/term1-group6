// 백준 1697 숨바꼭질
import java.io.*;
import java.util.*;
public class BAEK_1697 {
	
	static class node {
		int value, cnt;

		public node(int value, int cnt) {
			this.value = value;
			this.cnt = cnt;
		}
	}
	
	static int ret, K;
	// 각 노드에 대해 최소의 경로로 도달한 값을 저장. (가지치기 용도)
	static int[] leachMap = new int[100001];
	static Queue<node> queue = new ArrayDeque<>();
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ret = Integer.MAX_VALUE;
		
		// 동생의 위치가 수빈보다 뒤에 있으면 빼기만 해서 도달해야 함
		if (K <= N) ret = N-K;
		else {
			queue.add(new node(N, 0));
			find();
		}
		
		System.out.println(ret);
	}

	// 이동 가능한 노드를 계속 queue에 담아 더 빠른 경로가 있는지 업데이트 한다.
	// 같은 노드에 또 도달하는 경우가 발생하는데, cnt가 더 크다면 다시 queue에 담지 않는다.
	private static void find() {
		while(!queue.isEmpty()) {
			node p = queue.poll();
		
			// 노드 번호가 정해진 위치를 벗어나거나, 더 적절한 경우를 잡을 수 없는 상태라면 return
			// p-1이 K를 넘어가는 케이스인 경우, 어떤 경우가 되든 다른 경우보다 오래 걸린다.
			if ( p.value<0 || p.value-1 > K || p.value > 100000) continue;
			
			// 동생을 만났다면 결과 업데이트
			if (p.value == K && ret > p.cnt) {
				ret = p.cnt;
				continue;
			}
				
			// 이전에 같은 노드에 도달한 경우에서 더 짧은 경로를 업데이트 했다면
			// 이후의 cnt는 볼 필요가 없으므로 return
			if (leachMap[p.value] != 0 && leachMap[p.value] <= p.cnt) continue;
			
			leachMap[p.value] = p.cnt;
			
			queue.add(new node(p.value+1, p.cnt+1));
			queue.add(new node(p.value-1, p.cnt+1));
			
			if (p.value*2 <= 100000) queue.add(new node(p.value*2, p.cnt+1));
		}	
	}
}
