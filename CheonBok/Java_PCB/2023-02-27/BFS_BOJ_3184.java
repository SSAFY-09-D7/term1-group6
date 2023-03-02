import java.io.*;
import java.util.*;
public class BAEK_3184 {
	
	// BFS 목적의 Queue에 담을 노드 정보 class
	static class nodes {
		int row, col;

		public nodes(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, M, w, o;
	static char[][] ground;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  // row
		M = Integer.parseInt(st.nextToken());  // col
		ground = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == 'v') w++;  // count wolf
				if (tmp.charAt(j) == 'o') o++;  // count sheep
				ground[i][j] = tmp.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// checking around the ground except #(fence)
				if (ground[i][j] == '.' || ground[i][j] == 'v' || ground[i][j] == 'o') {
					check(new nodes(i, j));
				}
			}
		}
		System.out.println(o+" "+w);
	}

	private static void check(nodes node) {
		Queue<nodes> queue = new ArrayDeque<>();
		queue.add(node);
		int wol = 0;
		int ool = 0;
		
		while(!queue.isEmpty()) {
			nodes tmp = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.row + dx[i];
				int ny = tmp.col + dy[i];
				
				if (nx<0 || nx>=N || ny<0 || ny>=M || ground[nx][ny]=='#' || ground[nx][ny] == 'X') continue;
				
				if (ground[nx][ny] == 'v') wol++;
				if (ground[nx][ny] == 'o') ool++;
				
				queue.add(new nodes(nx, ny));
				ground[nx][ny] = 'X'; // change X -> Don't use after
			}
		}
		
		if (wol >= ool) o -= ool;
		else if(wol < ool) w -= wol;
	}
}
