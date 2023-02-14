import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visited;
	static int[] sel;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N + 1];
		sel = new int[M];
		
		perm(0);
		System.out.println(sb);
	}
	private static void perm(int depth) {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(sel[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(visited[i] == false) {
				if(depth == 0 || sel[depth - 1] < i) {
					visited[i] = true;
					sel[depth] = i;
					perm(depth + 1);
					visited[i] = false;
				}

			}
		}
		
	}
}

