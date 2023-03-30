import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class SWEA_7465
{
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 자기 자신을 부모로 초기화
			parents = new int[N+1];
			for (int i = 1; i <=N; i++) parents[i] = i;
				
			
			for (int c = 0; c < M; c++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			// 자기 자신이 부모이다 = 자신을 기준으로 이웃이 형성되어 있다.
			int cnt = 0;
			for (int i = 1; i < N+1; i++) {
				if (parents[i] == i) cnt++;
			}
			
			//System.out.println(Arrays.toString(parents));
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb);
	}

	// 각 조직의 대표를 찾은 후, 기준에 따라 하나로 합치기
	private static void union(int a, int b) {
		int p1 = findneib(a);
		int p2 = findneib(b);
		
		// 두 그룹이 다른 이웃이라면 하나로 합쳐야 한다.
		// 기준-수가 작은 사람을 기준으로 모이는 무리라고 판단
		if (p1 != p2) {
			if (p1 > p2) parents[p1] = p2;
			else parents[p2] = p1;
		}
		
		
	}

	// 부모 노드를 저장할 때, 최상위 부모 노드로 저장할 수 있도록 한다.
	private static int findneib(int b) {
		if (parents[b] == b) return b;
		else return parents[b] = findneib(parents[b]);
	}
}