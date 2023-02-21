import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] rMap;
	
	static int[] dRow = {1, 0, -1, 0};
	static int[] dCol = {0, 1, 0, -1};
	
	static boolean[] visited;
	static int[] select;
	static ArrayList<Info> infos;
	
	static int min;
	// list 선언
	static class Info{
		int row, col, s;
		public Info(int row, int col, int s) {
			this.row = row;
			this.col = col;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		infos = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			Info info = new Info(r, c, s);
			infos.add(info);
		}
		
		min = 500000;
		visited = new boolean[K];
		select = new int[K];
		perm(0);
		
		// 어떻게 돌릴지 순차적으로 배열 돌리기
		
		// 돌린 배열에 한해서 최솟값 구하기
		System.out.println(min);
	}



	private static void rotate() {
		for(int i = 0; i < select.length; i++) {
			Info info = infos.get(select[i]);
			int r = info.row;
			int c = info.col;
			int s = info.s;
			
			int startRow = r - s;
			int startCol = c - s;
			
			int endRow = r + s;
			int endCol = c + s;
			
			for(int boxCount = 0; boxCount < s; boxCount++) {
				int dir = 0;
				int curRow = startRow + boxCount;
				int curCol = startCol + boxCount;
				
				int startValue = rMap[curRow][curCol];
				
				while(dir < 4) {
					int nextRow = curRow + dRow[dir];
					int nextCol = curCol + dCol[dir];
					if(nextRow < startRow + boxCount || nextCol < startCol + boxCount || 
							nextRow > endRow - boxCount || nextCol > endCol - boxCount) {
						dir++;
					}
					else {
						rMap[curRow][curCol] = rMap[nextRow][nextCol];
						curRow = nextRow;
						curCol = nextCol;
					}
				}
				rMap[startRow + boxCount][startCol + boxCount + 1] = startValue;
			}
			
		}
		
	}	
	
	private static int calc() {
		int eachMin = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int value = 0;
			for(int j = 0; j < M; j++) {
				value += rMap[i][j];
			}
			if(value < eachMin) eachMin = value;
		}
		return eachMin;
	}
	

	private static void perm(int idx) {
		if(idx == K) {
			rMap = Arrays.copyOf(map, map.length);
			// 배열 돌리기
			rotate();
			int val = calc();
			if(val < min) min = val;
			return;
		}
		
		// 순열 코드
		for(int i = 0; i < infos.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				select[idx] = i;
				perm(idx + 1);
				visited[i] = false;
			}
		}
		
	}
}
