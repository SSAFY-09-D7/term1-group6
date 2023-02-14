import java.io.*;
import java.util.*;

class Point{
	int row, col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static ArrayList<Integer> result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int count = 0;
		result = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
					count++;
				}
			}
		}
		
		Collections.sort(result);
		
		System.out.println(count);
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		
	}
	private static void bfs(int row, int col) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(row, col));
		visited[row][col] = true;
		int houseCount = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			houseCount++;
			for(int i = 0; i < 4; i++) {
				int nextRow = p.row + dRow[i];
				int nextCol = p.col + dCol[i];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
				
				if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
					q.add(new Point(nextRow,nextCol));
					visited[nextRow][nextCol] = true;
				}
			}
		}
		result.add(houseCount);
	}
}
