import java.io.*;
import java.util.*;


public class Solution{
	static int N, M;
	static char[][] map;
	static boolean[][] Pvisited;
	static boolean[][] Dvisited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	
	static class Point{
		int row, col, depth;
		public Point(int row, int col, int depth) {
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			Pvisited = new boolean[N][M];
			Dvisited = new boolean[N][M];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			
			ArrayList<Point> devils = new ArrayList<>();
			Point person = null;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 'S') person = new Point(i, j, 0);
					if(map[i][j] == '*') devils.add(new Point(i, j, 0));
				}
			}
			
			Queue<Point> q = new LinkedList<>();
			q.add(person);
			Pvisited[person.row][person.col] = true;
			
			for(int i = 0; i < devils.size(); i++) {
				q.add(devils.get(i));
				Dvisited[devils.get(i).row][devils.get(i).col] = true;
			}

			int result = 0;
			
			L: while(!q.isEmpty()) {
				Point p = q.poll();
				int curRow = p.row;
				int curCol = p.col;
				for(int i = 0; i < 4; i++) {
					int nextRow = curRow + dRow[i];
					int nextCol = curCol + dCol[i];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
					
					// 사람이 가는 길인 경우
					if(map[curRow][curCol] == 'S') {
						if(!Pvisited[nextRow][nextCol] && map[nextRow][nextCol] != '*' && map[nextRow][nextCol] != 'X') {
							if(map[nextRow][nextCol] == 'D') {
								result = p.depth + 1;
								break L;
							}
							q.add(new Point(nextRow, nextCol, p.depth + 1));
							map[nextRow][nextCol] = 'S';
							Pvisited[nextRow][nextCol] = true;
						}
					}
					
					// 악마의 손아귀일 경우
					if(map[curRow][curCol] == '*') {
						if(!Dvisited[nextRow][nextCol] && map[nextRow][nextCol] != 'D' && map[nextRow][nextCol] != 'X') {
							q.add(new Point(nextRow, nextCol, p.depth + 1));
							map[nextRow][nextCol] = '*';
							Dvisited[nextRow][nextCol] = true;
						}
					}
				}
			}
			
			if(result == 0) {
				System.out.printf("#%d GAME OVER\n",testCase);
			}
			else System.out.printf("#%d %d\n", testCase, result);
			
		}
	}
}