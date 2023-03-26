import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class SWEA_3289
{
	static int[] parents;
	static String ret;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());  // 원소 수
			int M = Integer.parseInt(st.nextToken());  // 연산 수
			ret = "";
			
			parents = new int[N+1];
			for (int j = 1; j <= N; j++) parents[j] = j;
				
			for (int r = 0; r < M; r++) {
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				
				// 입력 0 : 두 노드를 합쳐서 하나의 부모 노드 값을 갖도록 한다.
				// 입력 1 : 두 노드가 같은 부모(최상위)에 속하는지 확인한다.
				if (type == 0) union(x, y);
				if (type == 1) {
					int px = find(x);
					int py = find(y);
					if (px != py) ret += "0";
					else ret += "1";
				}
			}
			sb.append("#"+i+" "+ret+"\n");
		}
		
		//System.out.println(Arrays.toString(parents));
		System.out.println(sb);

	}

	private static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		
		// 수가 작은 값을 기준으로 합친다.
		if (px != py) {
			if (px > py) parents[px] = py;
			else parents[py] = px;
		}
	}

	// 부모 노드 값을 저장할 때, 최상의 부모 노드를 저장하도록 한다.
	private static int find(int d) {
		if (d == parents[d]) return d;
		else return parents[d] = find(parents[d]);
	}
}