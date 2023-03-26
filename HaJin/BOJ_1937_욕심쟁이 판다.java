import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int[][] arr;
	public static int[][] cnt;
	public static boolean[][] visited;
	public static int MAX = 1;
	public static int ANS = 0;
	public static int[] nr = {-1, 1, 0, 0};
	public static int[] nc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		cnt = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				MAX = Math.max(MAX, func(i, j));
				
			}
		}

		System.out.println(MAX);
	}
	
	public static int func(int i, int j) {
		
		if(visited[i][j]) {
			return cnt[i][j];
		}
		
		visited[i][j] = true;
		boolean flag = false;
		
		for(int p=0; p<4; p++) {
			int ii = i + nr[p];
			int jj = j + nc[p];
			
			if(ii>=0 && ii<N && jj>=0 && jj<N && arr[i][j]<arr[ii][jj]) {
				flag = true;
				cnt[i][j] =Math.max(cnt[i][j], func(ii, jj)+1 );
			}
		}
		
		if(!flag)
			return cnt[i][j] = 1;
		
		return cnt[i][j];
		
	}
}