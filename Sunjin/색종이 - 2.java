import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int count;
	static int[][] map;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		visited = new boolean[101][101];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 시작 꼭짓점의 좌표
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			draw(row, col);
		}
		
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				// 색종이가 존재하는 위치라면 둘레를 파악한다.
				if(map[i][j] != 0) {
					isEdge(i, j);	
				}
			}
		}
		sb.append(count);
		System.out.println(sb);
	}
	
	// 4방탐색을 하며 0이 있는 곳 만큼이 우리가 찾는 둘레의 길이다.
	private static void isEdge(int row, int col) {
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dRow[i];
			int nextCol = col + dCol[i];
			if(map[nextRow][nextCol] == 0) {
				count++;
			}
		}
	}

	// 지도에 그리는 함수
	// 10 * 10만큼의 색종이를 흰 천에 칠한다.
	private static void draw(int row, int col) {
		for(int r = row; r < row + 10; r++) {
			for(int c = col; c < col + 10; c++) {
				map[r][c] = map[r][c] + 1;
			}
		}
		
	}

}