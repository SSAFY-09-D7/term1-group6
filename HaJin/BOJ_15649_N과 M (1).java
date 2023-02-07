import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int n;
	public static int m;
	public static int[] ans;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n+1];
		ans = new int[m];
		
		func(0);
	}
	
	public static void func(int idx) {
		if(idx == m) {
			for(int a : ans)
				System.out.print(a + " ");
			System.out.println();
			
			return;
		}
		
		for(int i=1; i<=n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				ans[idx] = i;
				func(idx+1);
				visited[i] = false;
			}
		}
	}
}
