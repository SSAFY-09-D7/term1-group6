import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	static int n;
	static int m;
	static int[] arr;
	static int[] ans;

	// idx : arr 시작 인덱스
	// cnt : ans 인덱스
	public static void func(int idx, int cnt) {
		if(cnt == m) {
			for(int i=0; i<m; i++)
				sb.append(ans[i]).append(" ");
			sb.append("\n");
			
			return;
		}

		for(int i=1; i<=n; i++) {
			ans[cnt] = arr[i];
			func(i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[10];
		ans = new int[10];
		
		for(int i=1; i<=n; i++)
			arr[i] = i;
		
		func(1, 0);
		
		System.out.println(sb);
	}
}
